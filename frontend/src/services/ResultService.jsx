import axios from "axios";

const RESULTS_URL = "http://localhost:8082/results";

class ResultService {
    getResults(){
        return axios.get(RESULTS_URL);
    }

    getResultByUserId(id){
        return axios.get(RESULTS_URL + '/' + id);
    }
}

export default new ResultService();