import React, { useEffect, useState } from "react";
import { useLocation, useNavigate, useParams } from "react-router-dom";
import "./ManageQuestion.css";
import AdminNavBar from '../../components/NavBars/AdminNavBar';
import { FaBackward, FaPen, FaPlusCircle, FaTrash } from "react-icons/fa";
import NotFound from '../HomePage/NotFound';
import QuestionService from "../../services/QuestionService";
import SweetAlert from "../../components/SweetAlerts/SweetAlert";
import InputComponent from "../../components/FormElements/InputComponent";
import SelectComponent from "../../components/FormElements/SelectComponent";
import TextAreaComponent from "../../components/FormElements/TextAreaComponent";
import ButtonComponent from "../../components/ButtonComponents/ButtonComponent";
import IconButton from "../../components/ButtonComponents/IconButton";
import Header1 from "../../components/HeaderComponents/Header1";
import DisableButtonComponent from "../../components/ButtonComponents/DisableButtonComponent";

const ManageQuestion = () => {
    const [questions, setQuestions] = useState([]);
    const [questionType, setQuestionType] = useState("MCQ");
    const location = useLocation();
    const userRole = localStorage.getItem("role");
    const categoryId = location.state?.categoryId;
    const quizName = location.state?.quizName;
    const numOfQuestions = location.state ? location.state.numOfQuestions : null;
    const { quizId } = useParams();
    const navigate = useNavigate();
    const [isAddingQuestion, setIsAddingQuestion] = useState(false);
    const [isEditingQuestion, setIsEditingQuestion] = useState(false);
    const [editedQuestion, setEditedQuestion] = useState({});
    const [newQuestion, setNewQuestion] = useState({
        questionTitle: "",
        option1: "",
        option2: "",
        option3: "",
        option4: "",
        correctOption: "",
        quizId,
    });

    const handleSelectMCQ = () => {
        setQuestionType("MCQ");
      };
      
    const handleSelectAssertion = () => {
        setQuestionType("ASSERT");
    };
      
    useEffect(() => {
        fetchQuestions();
    }, []);

    const fetchQuestions = () => {
        QuestionService.getQuestionsByQuizId(quizId)
        .then((response) => {
            setQuestions(response.data.body);
        })
    };

    const backButton = () => {
        navigate(`/manage-quiz/${categoryId}`);
    }

    const handleInputChange = (event) => {
        const { name, value } = event.target;
        if (isEditingQuestion) {
            setEditedQuestion({ ...editedQuestion, [name]: value });
        } else {
           setNewQuestion({ ...newQuestion, [name]: value });
        }
    };

    const handleAddQuestion = () => {
        if(!newQuestion.questionTitle || !newQuestion.option1 || !newQuestion.option2 || !newQuestion.option3
            || !newQuestion.option4 || !newQuestion.correctOption){
                SweetAlert.missingField();
                return;
        }

        QuestionService.addQuestion(newQuestion)
        .then(() => {
            SweetAlert.successAlert("Added");
            fetchQuestions();
            setNewQuestion({
                questionTitle: "",
                option1: "",
                option2: "",
                option3: "",
                option4: "",
                correctOption: "",
                quizId,
            });
            setIsAddingQuestion(false);
        })
        .catch((error) => {
            if(error?.response?.data?.message === "Options should not be repeated"){
                SweetAlert.duplicateOptions();
            }
        });
    };

    const handleAddAssertQuestion = () => {
        if(!newQuestion.questionTitle || !newQuestion.option1 || !newQuestion.option2 || !newQuestion.correctOption) {
                SweetAlert.missingField();
                return;
        }

        QuestionService.addAssertQuestion(newQuestion)
        .then(() => {
            SweetAlert.successAlert("Added");
            fetchQuestions();
            setNewQuestion({
                questionTitle: "",
                option1: "",
                option2: "",
                correctOption: "",
                quizId,
            });
            setIsAddingQuestion(false);
        })
        .catch((error) => {
            console.log(error);
            if(error?.response?.data?.message === "Options should not be repeated"){
                SweetAlert.duplicateOptions();
            }
        });
    };

    const handleEditQuestion = () => {
        if(!editedQuestion.questionTitle || !editedQuestion.option1 || !editedQuestion.option2 || !editedQuestion.option3
            || !editedQuestion.option4 || !editedQuestion.correctOption){
                SweetAlert.missingField();
                return;
        }
        QuestionService.updateQuestion(editedQuestion.questionId, editedQuestion)
        .then(() => {
            
            SweetAlert.successAlert("Updated");
            fetchQuestions();
            setIsEditingQuestion(false);
        })
        .catch((error) => {
            if(error?.response?.data?.message === "Options should not be repeated"){
                SweetAlert.duplicateOptions();
            }
        });
    };

    const handleEditAssertQuestion = () => {
        if(!editedQuestion.questionTitle || !editedQuestion.option1 || !editedQuestion.option2 || !editedQuestion.correctOption){
                SweetAlert.missingField();
                return;
        }

        QuestionService.updateAssertQuestion(editedQuestion.questionId, editedQuestion)
        .then(() => {
            SweetAlert.successAlert("Updated");
            fetchQuestions();
            setIsEditingQuestion(false);
        })
        .catch((error) => {
            console.log(error);
            if(error?.response?.data?.message === "Options should not be repeated"){
                SweetAlert.duplicateOptions();
            }
        });
    };

    const handleDeleteQuestion = (questionId) => {
        QuestionService.deleteQuestion(questionId)
        .then(() => {
            fetchQuestions();
        })
    };

    const handleDeleteButton = (questionId) => {
        SweetAlert.deleteAlert("Question", questionId, handleDeleteQuestion)
    }

    const handleEditClick = (question) => {
        setEditedQuestion({ ...question });
        setIsEditingQuestion(true);


        const numOptions = [
            question.option1,
            question.option2,
            question.option3,
            question.option4
        ].filter(option => option).length;

        if (numOptions === 2) {
            setQuestionType("ASSERT");
        } else if (numOptions === 4) {
            setQuestionType("MCQ");
        }
    };

    return (
        <div className="App">
            {userRole === "ADMIN" ? (
            <>
                <AdminNavBar/>
                <div className="manage-questions-container">
                    <Header1 text = {!(isAddingQuestion || isEditingQuestion) ? (questions.length === 0 ? `No Questions Available - ${quizName}` :`Manage Questions - ${quizName} `) : ("")} className="arial"/>
                    {!isAddingQuestion && !isEditingQuestion ? (
                        <div className="button-container">

                            <IconButton 
                            className = "red-button button" 
                            onClick = {backButton} 
                            text = "Back" 
                            icon = {<FaBackward className="small-icon" />}
                            iconLeft={true}
                            />

                            <IconButton 
                            className = "blue-button button right" 
                            onClick={questions.length < numOfQuestions ? (() => setIsAddingQuestion(true)) : (() => SweetAlert.limitReached()) } 
                            text = "Add " 
                            icon = {<FaPlusCircle className="small-icon" />}
                            iconLeft={false}
                            />

                        </div>

                    ) : (

                        <div className="add-question-container">
                            <div className={`question-form ${isAddingQuestion || isEditingQuestion ? 'active' : ''}`}>
                                <Header1 text = {(isAddingQuestion ? 'Add Question' : 'Edit Question')} className = "arial" />
                                <div className="question-type-buttons">
                                    <DisableButtonComponent
                                        onClick={handleSelectMCQ}
                                        className={
                                            questionType === "MCQ" 
                                                ? "button-box question-selected" 
                                                : `button-box question-unselected ${
                                                    isEditingQuestion && questionType === "ASSERT"
                                                        ? "disable-button-disabled"
                                                        : ""
                                            }`
                                        }
                                        text = "MCQ"
                                        disabled={isEditingQuestion && questionType === "ASSERT"}
                                    />

                                    <DisableButtonComponent
                                        onClick={handleSelectAssertion}
                                        className={
                                            questionType === "ASSERT"
                                                ? "button-box question-selected"
                                                : `button-box question-unselected ${
                                                    isEditingQuestion && questionType === "MCQ"
                                                        ? "disable-button-disabled"
                                                        : ""
                                                }`
                                        }
                                        text="ASSERT"
                                        disabled={isEditingQuestion && questionType === "MCQ"}
                                    />

                                </div>
                                {questionType === "MCQ" && (
                                    <div>
                                        <TextAreaComponent
                                        className = "reg-input-fields-question"
                                        name="questionTitle"
                                        placeholder="Question title"
                                        value={isEditingQuestion ? editedQuestion.questionTitle : newQuestion.questionTitle}
                                        onChange={handleInputChange}
                                        />

                                        <InputComponent
                                        type="text"
                                        className = "reg-input-fields-question"
                                        name="option1"
                                        placeholder="Option 1"
                                        value={isEditingQuestion ? editedQuestion.option1 : newQuestion.option1}
                                        onChange={handleInputChange}
                                        />

                                        <InputComponent
                                        type="text"
                                        className = "reg-input-fields-question"
                                        name="option2"
                                        placeholder="Option 2"
                                        value={isEditingQuestion ? editedQuestion.option2 : newQuestion.option2}
                                        onChange={handleInputChange}
                                        />

                                        <InputComponent
                                        type="text"
                                        className = "reg-input-fields-question"
                                        name="option3"
                                        placeholder="Option 3"
                                        value={isEditingQuestion ? editedQuestion.option3 : newQuestion.option3}
                                        onChange={handleInputChange}
                                        />

                                        <InputComponent
                                        type="text"
                                        className = "reg-input-fields-question"
                                        name="option4"
                                        placeholder="Option 4"
                                        value={isEditingQuestion ? editedQuestion.option4 : newQuestion.option4}
                                        onChange={handleInputChange}
                                        />
                                        
                                        <SelectComponent
                                        isEditingQuestion={isEditingQuestion}
                                        editedQuestion={editedQuestion}
                                        newQuestion={newQuestion}
                                        handleInputChange={handleInputChange}
                                        questiontype={questionType}
                                        />

                                        <div className="form-group">
                                            <div className="button-container-category">
                                                {isAddingQuestion ? (
                                                    <ButtonComponent className="button blue-button" onClick={handleAddQuestion} text = "Add" />
                                                ) : (
                                                    <ButtonComponent className="button blue-button" onClick={handleEditQuestion} text = "Update"/>
                                                )}
                                            
                                                <ButtonComponent className="button red-button" onClick={() => {
                                                setIsAddingQuestion(false);
                                                setIsEditingQuestion(false);
                                                setEditedQuestion({});
                                                }} text = "Cancel" 
                                                />
                                            </div>
                                        </div>
                                    </div>
                                )}
                                {questionType === "ASSERT" && (
                                    <div>
                                        <TextAreaComponent
                                        className = "reg-input-fields-question"
                                        name="questionTitle"
                                        placeholder="Question title"
                                        value={isEditingQuestion ? editedQuestion.questionTitle : newQuestion.questionTitle}
                                        onChange={handleInputChange}
                                        />

                                        <InputComponent
                                        type="text"
                                        className = "reg-input-fields-question"
                                        name="option1"
                                        placeholder="Option 1"
                                        value={isEditingQuestion ? editedQuestion.option1 : newQuestion.option1}
                                        onChange={handleInputChange}
                                        />

                                        <InputComponent
                                        type="text"
                                        className = "reg-input-fields-question"
                                        name="option2"
                                        placeholder="Option 2"
                                        value={isEditingQuestion ? editedQuestion.option2 : newQuestion.option2}
                                        onChange={handleInputChange}
                                        />          
                                                                      
                                        <SelectComponent
                                        isEditingQuestion={isEditingQuestion}
                                        editedQuestion={editedQuestion}
                                        newQuestion={newQuestion}
                                        handleInputChange={handleInputChange}
                                        questiontype={questionType}
                                        />

                                        <div className="form-group">
                                            <div className="button-container-category">
                                                {isAddingQuestion ? (
                                                    <ButtonComponent className="button blue-button" onClick={handleAddAssertQuestion} text = "Add" />
                                                ) : (
                                                    <ButtonComponent className="button blue-button" onClick={handleEditAssertQuestion} text = "Update"/>
                                                )}
                                            
                                                <ButtonComponent className="button red-button" onClick={() => {
                                                setIsAddingQuestion(false);
                                                setIsEditingQuestion(false);
                                                setEditedQuestion({});
                                                }} text = "Cancel" 
                                                />
                                            </div>
                                        </div>
                                    </div>
                                )}
                            </div>
                        </div>
                    )}
                    
                    {isAddingQuestion || isEditingQuestion ? null : (
                    <div className="question-list-container">
                        <div className="question-list">
                            <ul>
                                
                                {questions.map((question) => (
                                <li key={question.questionId}>
                                    <div className="question-title">{question.questionTitle}</div>
                                    <div className="question-options">
                                        <div>{question.option1}</div>
                                        <div>{question.option2}</div>
                                        <div>{question.option3}</div>
                                        <div>{question.option4}</div>
                                        <div>Correct : {question.correctOption}</div>
                                    </div>
                                    <div className="question-actions">

                                        <IconButton
                                        className = "delete-button button-small item"
                                        onClick = {() => handleDeleteButton(question.questionId)}
                                        icon = {<FaTrash className="very-small-icon"/>}
                                        text = "Delete"
                                        iconLeft={false}
                                        />

                                        <IconButton
                                        className = "open-button button-small"
                                        onClick={() => handleEditClick(question)}
                                        text = "Edit "
                                        icon = {<FaPen className="very-small-icon"/>}
                                        iconLeft={false}
                                        />

                                    </div>
                                </li>
                                ))}

                            </ul>
                        </div>
                    </div>
                    )}
                    
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

export default ManageQuestion;