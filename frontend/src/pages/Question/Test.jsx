import React, { useEffect, useState } from 'react';
import './Test.css';
import { useLocation, useNavigate, useParams } from 'react-router-dom';
import Swal from 'sweetalert2';
import clockswal from "../../assets/image/clockswal.png";
import oopsswal from "../../assets/image/oopsswal.png";
import instructionswal from "../../assets/image/instructionswal.png";
import DeactivateBackButton from '../../components/ButtonComponents/DeactivateBackButton';
import NotFound from '../../pages/HomePage/NotFound';
import SweetAlert from '../../components/SweetAlerts/SweetAlert';
import QuestionService from '../../services/QuestionService';
import ResponseService from '../../services/ResponseService';
import { format } from 'date-fns';
import TimerNavBar from '../../components/NavBars/TimerNavBar';
import ButtonComponent from "../../components/ButtonComponents/ButtonComponent";
import { FaBars, FaTimes } from 'react-icons/fa';

const Test = () => {

    const { quizId } = useParams();
    const navigate = useNavigate();
    const location = useLocation();
    const { timeInMin } = location.state || {};
    const categoryId = localStorage.getItem("categoryId");
    const userId = localStorage.getItem("id");
    const userRole = localStorage.getItem("role");
    const [questions, setQuestions] = useState([]);
    const [loading, setLoading] = useState(true);
    const totalMarks = questions.length * 2;
    const [autoSubmitted, setAutoSubmitted] = useState(false);
    const [instructionsConfirmed, setInstructionsConfirmed] = useState(false);
    const [timer, setTimer] = useState(timeInMin * 60);

    const navigatetoProfile = () => {
        setTimeout(() => {
            navigate("/profile");
        }, 1000);
    }

    const [numOfQuestionsAnswered, setNumOfQuestionsAnswered] = useState(() => {
        const storedNumberOfAttempted = localStorage.getItem('numberOfAttempted');
        return storedNumberOfAttempted ? parseInt(storedNumberOfAttempted) : 0;
    }); 

    const [marksScored, setMarksScored] = useState(() => {
        const storedMarksScored = localStorage.getItem('marksScored');
        return storedMarksScored ? parseInt(storedMarksScored) : 0;
    });

    const [startTime, setStartTime] = useState(() => {
        const storedStartTime = localStorage.getItem('startTime');
        return storedStartTime ? parseInt(storedStartTime) : null;
    });

    const [selectedOptions, setSelectedOptions] = useState([]);
    const [currentQuestionIndex, setCurrentQuestionIndex] = useState(0);
    const [showNavigation, setShowNavigation] = useState(false);

    const [viewed, setViewed] = useState(Array(questions.length).fill(false));

    const markQuestionAsVisited = (index) => {
    const updatedViewed = [...viewed];
    updatedViewed[index] = true;
    setViewed(updatedViewed);
    };

    const questionStatus = questions.map((_, index) => {

        const isCurrentQuestion = currentQuestionIndex === index;
        const isAnswered = selectedOptions[index] !== undefined;
        const isVisited = viewed[index] || isCurrentQuestion;

        if (isCurrentQuestion) {
            return 'current';
        } else if (isVisited) {
            return isAnswered ? 'answered' : 'visited';
        } else {
            return 'unvisited';
        }
        });

    const navigateToQuestion = (questionIndex) => {
        setCurrentQuestionIndex(questionIndex);
        setShowNavigation(false);
        markQuestionAsVisited(questionIndex);
    };

    const handleNextQuestion = () => {
        if(currentQuestionIndex < questions.length - 1){
            setCurrentQuestionIndex(currentQuestionIndex + 1);
            markQuestionAsVisited(currentQuestionIndex);
        }
    };

    const handlePreviousQuestion = () => {
        if(currentQuestionIndex > 0){
            setCurrentQuestionIndex(currentQuestionIndex - 1);
        }
    }

    const formatTime = (timeInSeconds) => {
        const minutes = Math.floor(timeInSeconds / 60);
        const seconds = timeInSeconds % 60;
        return `${minutes}:${seconds < 10 ? `0${seconds}` : seconds}`;
    };

    const fetchQuestions = () => {
        QuestionService.getQuestionsByQuizId(quizId)
        .then((response) => {
            if (response.data.body.length === 0) {
                Swal.fire({
                    title: "No Questions available",
                    text: "Kindly take another Quiz",
                    imageUrl: oopsswal,
                    imageHeight: 100,
                    imageWidth: 150,
                }).then(() => {
                    navigate(`/manage-quiz/${categoryId}`);
                })
            } else {
                setQuestions(response.data.body);
                setLoading(false);
            }
        })
    };


    useEffect(() => {
        if (userRole === "USER") {
            fetchQuestions();
        }
    }, [quizId]);

    
    const handleOptionSelect = (option, questionIndex) => {
        const updatedSelectedOptions = [...selectedOptions];
        if (updatedSelectedOptions[questionIndex] === option) {
          updatedSelectedOptions[questionIndex] = null;
          markQuestionAsVisited(questionIndex);
        } else {
          updatedSelectedOptions[questionIndex] = option;
        }
      

        setSelectedOptions(updatedSelectedOptions);
      
        const answeredQuestions = updatedSelectedOptions.filter(Boolean).length;
        setNumOfQuestionsAnswered(answeredQuestions);
      

        const updatedViewed = [...viewed];
        if (updatedViewed[questionIndex] === true) {
          updatedViewed[questionIndex] = 'visited';
        } else {
          updatedViewed[questionIndex] = updatedSelectedOptions[questionIndex] !== null;
        }
        setViewed(updatedViewed);
      
        let score = 0;
        for (let i = 0; i < questions.length; i++) {
          const correctOption = questions[i].correctOption;
          const selectedOption = updatedSelectedOptions[i];
      
          if (selectedOption === correctOption) {
            score += 2;
          }
        }
      
        setMarksScored(score);
      
        localStorage.setItem('numberOfAttempted', answeredQuestions.toString());
        localStorage.setItem('marksScored', score.toString());
      };
    

    const clearLocalStorage = () => {
        localStorage.removeItem('startTime');
        localStorage.removeItem('instructionShown');
        localStorage.removeItem("pageRefreshed");
        localStorage.removeItem('numberOfAttempted');
        localStorage.removeItem('marksScored');
    }

    const handleAddResponses = async () => {
        const formattedTime = format(new Date(startTime), 'dd-MM-yyyy HH:mm:ss');
        const data = {
            userId,
            quizId,
            categoryId,
            numOfQuestions: questions.length,
            numOfQuestionsAnswered,
            totalMarks,
            marksScored,
            timeStamp: formattedTime
        };
        await ResponseService.postResponse(data);
    };

    const handleSubmit = () => {
        handleAddResponses();
        Swal.fire({
            title: "Time up",
            text: "The quiz is submitted",
            imageUrl: clockswal,
            allowOutsideClick : false,
            showConfirmButton : true,
            imageHeight: 150,
            imageWidth: 150,
        }).then(() => {
                navigatetoProfile();
                clearLocalStorage();
                SweetAlert.redirecting(() => {navigate("/profile")});
        })
    };

    useEffect(() => {
        if (userRole === "USER") {
            const instructionShown = localStorage.getItem("instructionShown");
            if (questions.length > 0 && !instructionsConfirmed && !instructionShown) {
                Swal.fire({
                    title: "Instructions for the Test",
                    width: "900px",
                    padding: '3em',
                    color: 'black',
                    backdrop: `
                        rgb(240, 240, 240, 0.8)
                    `,
                    imageUrl: instructionswal,
                    imageWidth: 100,
                    allowOutsideClick: false,
                    showConfirmButton: true,
                    confirmButtonText: "Start",
                    confirmButtonColor : "green",
                    showCancelButton : true,
                    cancelButtonText: "Cancel",
                    cancelButtonColor : "red",
                    customClass : {
                        confirmButton : "custom-swal-button",
                        cancelButton : "custom-swal-button"
                    },
                    html: `
                        <ol>
                        <li>Each Question carries Two Marks.</li><br>
                        <li>This is a Timed Test. Hence Have a look at the timer.</li><br>
                        <li>Questions are of "Choose the Correct Answer" type.</li><br>
                        <li>There is no negative marking.</li><br>
                        <b><li><p style="text-align : left; margin-top : 2px;"><strong>NOTE: If you reload or Shift to other tab, Test gets submitted.</strong></b>
                        </ol>
                    `,
                }).then((response) => {
                    if (response.isConfirmed) {
                        setInstructionsConfirmed(true);
                        const currentTime = new Date().getTime();
                        setStartTime(currentTime);

                        localStorage.setItem("instructionShown", "true");
                        localStorage.setItem("startTime", currentTime.toString());
                    } else{
                        clearLocalStorage();
                        navigate(`/manage-quiz/${categoryId}`)
                    }
                });

            } else {

                const pageRefreshed = localStorage.getItem("pageRefreshed");
                if (pageRefreshed) {
                    Swal.fire({
                        title: "Page Reloaded",
                        text: "Your answers have been automatically submitted upon page reload",
                        icon: "info",
                        showConfirmButton: true,
                        confirmButtonColor : "green",
                        allowOutsideClick: false,
                        backdrop: 
                        `
                            rgb(200, 200, 200, 1)
                        `,
                        confirmButtonText: "Ok",
                        customClass:{
                            confirmButton : "custom-swal-button"
                        }

                    }).then((result) => {
                        if (result.isConfirmed) {
                            handleAddResponses();
                            clearLocalStorage();
                            navigatetoProfile();
                            SweetAlert.redirecting(()=> {navigate("/profile")});
                        }
                    });
                }
            }
        }
    }, [userRole, questions, instructionsConfirmed]);


    useEffect(() => {
        if (userRole === "USER" && instructionsConfirmed) {
            let interval;

            const startTimer = () => {
                if (startTime) {
                    const currentTime = new Date().getTime();
                    const elapsedTimeInSeconds = Math.floor((currentTime - startTime) / 1000);
                    const remainingTime = Math.max(timeInMin * 60 - elapsedTimeInSeconds, 0);

                    setTimer(remainingTime);

                    if (remainingTime <= 0 && !autoSubmitted) {
                        setAutoSubmitted(true);
                        handleSubmit();
                        clearInterval(interval);
                    }
                }
            };

            if (!startTime) {
                const currentTime = new Date().getTime();
                setStartTime(currentTime);
                localStorage.setItem('startTime', currentTime.toString());
            }

            interval = setInterval(startTimer, 1000);
            return () => {
                clearInterval(interval);
            };
        }
    }, [instructionsConfirmed, autoSubmitted, handleSubmit, timeInMin, userRole, startTime]);

    useEffect(() => {
        const handleVisibilityChange = () => {
          if (document.visibilityState === 'hidden') {
            if (!autoSubmitted) {
              setAutoSubmitted(true);
              Swal.fire({
                title: "Suspicious Activity",
                text: "Your answers have been automatically submitted",
                icon: "info",
                showConfirmButton: true,
                confirmButtonColor : "green",
                allowOutsideClick: false,
                backdrop: 
                `
                    rgb(200, 200, 200, 1)
                `,
                confirmButtonText: "Ok",
                customClass:{
                    confirmButton : "custom-swal-button"
                }

            }).then((result) => {
                if (result.isConfirmed) {
                    handleAddResponses();
                    clearLocalStorage();
                    navigatetoProfile();
                    SweetAlert.redirecting(()=> {navigate("/profile")});
                }
            });
            }
          }
        };
        document.addEventListener('visibilitychange', handleVisibilityChange);
        return () => {
          document.removeEventListener('visibilitychange', handleVisibilityChange);
        };
      }, [autoSubmitted, handleSubmit]);
      

    const handleManualSubmit = () => {
        if (numOfQuestionsAnswered === 0) {
            Swal.fire({
                title: "UnAttempted Test",
                text: "Unable to Submit as You have not attempted the test",
                icon: "error",
            })
        } else {
            Swal.fire({
                title: "Do you want to submit",
                text: "Once you submit, You will be redirected out",
                icon: "warning",
                showConfirmButton: true,
                confirmButtonText: "Ok",
                showCancelButton: true,
                cancelButtonText: "Cancel"
            }).then((result) => {
                if (result.isConfirmed) {
                    handleAddResponses();
                    clearLocalStorage();
                    navigatetoProfile();
                    SweetAlert.redirecting(() => {navigate("/profile")});
                }
            });
        }
    };

    useEffect(() => {
        const handleBeforeUnload = () => {        
            localStorage.setItem("pageRefreshed", "true");
        };
        window.addEventListener('beforeunload', handleBeforeUnload);
        return () => {
            window.removeEventListener('beforeunload', handleBeforeUnload);
        };
    }, []);

    return (
        <div className='no-select'>
            <DeactivateBackButton />
            <div className="navigation-toggle" onClick={() => setShowNavigation(!showNavigation)}>
                {showNavigation ? <FaTimes /> : <FaBars />}
            </div>
            {showNavigation && (
                <div className="question-navigation">
                    <div className='question-grid'>
                        {questions.map((_, index) => (
                            <div
                                key={index}
                                className={`question-number ${questionStatus[index]}`}
                                onClick={() => navigateToQuestion(index)}
                            >
                                {index + 1}
                            </div>
                        ))}
                    </div>  
                </div>
            )}
            {(questions.length > 0 && (
                <TimerNavBar timerValue={formatTime(timer)} className={timer < (timeInMin * 10) ? "timer-out" : "timer"} />
            ))}
            {(userRole === "USER" ? (
                <div className="quiz-container">
                    {loading ? (
                        <div>Loading questions... No Questions as of now</div>
                    ) : questions.length > 0 ? (
                        <div className="question-container">
                            <div className="question-content">
                                <p><b>{currentQuestionIndex + 1}. {questions[currentQuestionIndex].questionTitle}</b></p>
                                <div className="options">
                                {Array.from({ length: 4 }, (_, optionIndex) => {
                                    const optionKey = `option${optionIndex + 1}`;
                                    const optionContent = questions[currentQuestionIndex][optionKey];
                                    if (optionContent) {
                                        return (
                                            <div
                                                key={optionIndex}
                                                className={`option ${selectedOptions[currentQuestionIndex] === optionContent ? 'selected' : null}`}
                                                onClick={() => handleOptionSelect(optionContent, currentQuestionIndex)}
                                            >
                                                {optionContent}
                                            </div>
                                        );
                                    } else {
                                        return null;
                                    }
                                })}

                                </div>
                            </div>
                            <div className="navigation-buttons">
                                <ButtonComponent onClick={handlePreviousQuestion} className = {currentQuestionIndex === 0 ? "button disabled" : "blue-button button"} text="Previous"/>
                                <ButtonComponent onClick={currentQuestionIndex === questions.length - 1 ? handleManualSubmit : handleNextQuestion} className="blue-button button" text={currentQuestionIndex === questions.length - 1 ? "Submit" : "Next"} />
                            </div>
                        </div>
                    ) : (
                        <div>
                            No questions available.
                        </div>
                    )}
                </div>
            ) : (
                <>
                    <NotFound />
                </>
            ))}
        </div>
    );

};

export default Test;