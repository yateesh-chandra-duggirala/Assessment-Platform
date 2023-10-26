import React, { useEffect, useState } from "react";
import { useNavigate, useParams } from "react-router-dom";
import AdminNavBar from '../../components/NavBars/AdminNavBar';
import {useLocation} from 'react-router-dom'
import NotFound from "../../pages/HomePage/NotFound";
import QuizService from "../../services/QuizService";
import SweetAlert from "../../components/SweetAlerts/SweetAlert";
import LabelComponent from "../../components/FormElements/LabelComponent";
import InputComponent from "../../components/FormElements/InputComponent";
import TextAreaComponent from "../../components/FormElements/TextAreaComponent";
import Header1 from "../../components/HeaderComponents/Header1";
import ButtonComponent from "../../components/ButtonComponents/ButtonComponent";

const AddOrUpdateQuiz = () => {
    const { quizId } = useParams();
    const navigate = useNavigate();
    const location = useLocation();
    const categoryId = location.state?.categoryId;
    const isUpdating = quizId !== undefined;
    const userRole = localStorage.getItem("role");

    const [quizName, setQuizName] = useState("");
    const [quizDescription, setQuizDescription] = useState("");
    const [numOfQuestions, setNumOfQuestions] = useState(0);
    const [timeInMin, setTimeInMin] = useState(0);

    const [quizNameError, setQuizNameError] = useState("");
    const [quizDescriptionError, setQuizDescriptionError] = useState("");
    const [numOfQuestionsError, setNumOfQuestionsError] = useState("");
    const [timeInMinError, setTimeInMinError] = useState("");

    const cancelButton = () => {
        navigate(`/manage-quiz/${categoryId}`);
    };

    const fetchQuizData = () => {
        QuizService.getQuizById(quizId)
        .then((response) => {
            const { quizName, quizDescription, numOfQuestions, timeInMin} = response.data.body;
            setQuizName(quizName);
            setQuizDescription(quizDescription);
            setNumOfQuestions(numOfQuestions);
            setTimeInMin(timeInMin);
        })
    };

    const handleQuizNameChange = (e) => {
        const validName = e.target.value;
        setQuizName(validName);
        if (!validName) {
            setQuizNameError("Quiz Name is required");
        } else {
            setQuizNameError("");
        }
    };

    const handleQuizDescriptionChange = (e) => {
      const validDescription = e.target.value;
      setQuizDescription(validDescription);
      if (!validDescription) {
        setQuizDescriptionError("Add some Description");
      } else {
        setQuizDescriptionError("");
      }
    };

    const handleQuizNumOfQuestions = (e) => {
        const validNumOfQuestions = parseInt(e.target.value, 10);
        setNumOfQuestions(validNumOfQuestions);
        if (isNaN(validNumOfQuestions) || validNumOfQuestions <= 0) {
            setNumOfQuestionsError("Enter a valid number of Questions");
        } else {
            setNumOfQuestionsError("");
        }
    };

    const handleQuizDuration = (e) => {
        const validTimeInMin = parseInt(e.target.value, 10);
        setTimeInMin(validTimeInMin);
        if (isNaN(validTimeInMin) || validTimeInMin <= 0) {
            setTimeInMinError("Enter valid Duration in Minutes");
        } else {
            setTimeInMinError("");
        }
    };

    const validForm = () => {
        let isValid = true;
        if (!quizName) {
            setQuizNameError("Quiz Name is required");
            isValid = false;
        } else if(quizName.startsWith(" ")){
            setQuizNameError("Name cannot start with spaces");
            isValid = false;
        } else{
            setQuizNameError("");
        }

        if (!quizDescription) {
            setQuizDescriptionError("Add some Description");
            isValid = false;
        } else {
            setQuizDescriptionError("");
        }

        if(!numOfQuestions){
            setNumOfQuestionsError("Enter Number");
            isValid = false;
        }
        else if (isNaN(numOfQuestions) || numOfQuestions <= 0) {
            setNumOfQuestionsError("Enter a valid number of Questions");
            isValid = false;
        } else {
            setNumOfQuestionsError("");
        }

        if(!timeInMin){
            setTimeInMinError("Enter Number");
            isValid = false;
        }
        else if (isNaN(timeInMin) || timeInMin <= 0){
            setTimeInMinError("Enter a valid number for Duration of Minutes");
            isValid = false;
        } else {
            setTimeInMinError("");
        }

        return isValid;
    };

    const handleAddOrUpdateQuiz = async (e) => {
        e.preventDefault();
        if (!validForm()) {
            SweetAlert.missingField();
            return;
        }

        const quizData = {
            quizName,
            quizDescription,
            numOfQuestions,
            timeInMin,
            categoryId,
        };

        if (isUpdating) {
            try{
                await QuizService.updateQuiz(quizId, quizData);
                SweetAlert.successAlert("Updated");
                navigate(`/manage-quiz/${categoryId}`);
                fetchQuizData();
            } catch(error) {  
                if(error?.response?.data?.message === "Quiz already Exists"){
                    setQuizNameError("Quiz already exists");
                    SweetAlert.alreadyExists("Quiz");
            
                } else if(error?.response?.data?.message === "Number cannot be 0 or less"){
                    SweetAlert.incorrectValues();
                } else {
                    console.error(error);
                }
            }
        } else {
            try{
                await QuizService.addQuiz(quizData);            
                SweetAlert.successAlert("Added");
                navigate(`/manage-quiz/${categoryId}`);
            } catch(error) {
                if(error?.response?.data?.message === "Quiz already Exists" ){
                    setQuizNameError("Quiz already exists");
                    SweetAlert.alreadyExists("Quiz");
                } 
                else if(error?.response?.data?.message === "Number cannot be 0 or less"){
                    SweetAlert.incorrectValues();
                }
                else{
                    console.error(error);
                }
            }
        }
    };

    useEffect(() => {
        if(isUpdating){
            fetchQuizData();
        }
    },[]);

    return (
        <div className="App">
            {userRole === "ADMIN" ? (
            <>
                <AdminNavBar />
                <div className="add-quiz-container">
                    <Header1 className = "arial" text = {isUpdating ? 'Update Quiz' : 'Add Quiz'}/>
                    <form onSubmit={handleAddOrUpdateQuiz}>
                        <div className="form-group">

                            <LabelComponent className = "label" text = "Quiz Name:"/>
                            <InputComponent
                            className = "reg-input-fields"
                            type="text"
                            value={quizName}
                            onChange={handleQuizNameChange}
                            placeholder="Enter Quiz Name"
                            />
                            {quizNameError && <div className="error">{quizNameError}</div>}

                            <LabelComponent className = "label" text = "Quiz Description:"/>
                            <TextAreaComponent
                            className = "reg-input-fields"
                            value={quizDescription}
                            onChange={handleQuizDescriptionChange}
                            placeholder="Enter Quiz Description"
                            />
                            {quizDescriptionError && <div className="error">{quizDescriptionError}</div>}

                            <LabelComponent className = "label" text = "Number Of Questions:"/>
                            <InputComponent
                            type="number"
                            className = "reg-input-fields"
                            value={numOfQuestions}
                            onChange={handleQuizNumOfQuestions}
                            placeholder="Enter Number of Questions"
                            />
                            {numOfQuestionsError && <div className="error">{numOfQuestionsError}</div>}

                            <LabelComponent className = "label" text = "Duration (in Minutes):"/>
                            <InputComponent
                            type = "number"
                            className = "reg-input-fields"
                            value = {timeInMin}
                            onChange = {handleQuizDuration}
                            placeholder = "Enter Duration"
                            />
                            {timeInMinError && <div className="error">{timeInMinError}</div>}

                        </div>
                        
                        <div className="form-group">
                            <div className="button-container-category">
                                <ButtonComponent className = "button blue-button" type="submit" text = {isUpdating ? 'Update' : 'Add'} />
                                <ButtonComponent type="button" className="button red-button" onClick={cancelButton} text = "Cancel" />
                            </div>
                        </div>
                    </form>
                </div>
            </>
            ):(
                <div>
                    <NotFound/>
                </div>
            )}
        </div>
    );
};

export default AddOrUpdateQuiz;
