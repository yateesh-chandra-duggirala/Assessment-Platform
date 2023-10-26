import axios from "axios"

const RESPONSE_URL = "http://localhost:8082/response"

class ResponseService{
    postResponse(data){
        return axios.post(RESPONSE_URL + '/add', data);
    }
}

export default new ResponseService();