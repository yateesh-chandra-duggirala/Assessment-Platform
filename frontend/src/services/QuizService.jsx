import axios from "axios";

const QUIZ_URL = 'http://localhost:8082/quiz';

class QuizService{

    addQuiz(Quiz){
        return axios.post(QUIZ_URL, Quiz);
    }

    getQuizzes(){
        return axios.get(QUIZ_URL);
    }

    getQuizByCategoryId(categoryId){
        return axios.get(QUIZ_URL + '/byCategory/' + categoryId);
    }

    getQuizById(quizId){
        return axios.get(QUIZ_URL + '/' + quizId);
    }

    updateQuiz(quizId, Quiz){
        return axios.put(QUIZ_URL + '/'+ quizId , Quiz);
    }

    deleteQuiz(quizId){
        return axios.delete(QUIZ_URL + '/' + quizId);
    }
}

export default new QuizService();