import axios from "axios";

const USER_URL = "http://localhost:8082/user";

class UserServices{

    registerUser(User){
        return axios.post(USER_URL + "/save", User);
    }

    loginUser(User){
        return axios.post(USER_URL + "/login", User);
    }

    getUsers(){
        return axios.get(USER_URL);
    }
}

export default new UserServices();