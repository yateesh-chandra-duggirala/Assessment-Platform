import { useNavigate } from "react-router-dom";
import "./LoginRegistration.css";
import React, { useState } from "react";
import Swal from "sweetalert2";
import UserServices from "../../services/UserServices";
import SweetAlert from "../../components/SweetAlerts/SweetAlert";
import InputComponent from "../../components/FormElements/InputComponent";

import { FaEye, FaEyeSlash } from "react-icons/fa";
import ButtonComponent from "../../components/ButtonComponents/ButtonComponent";
import Header2 from "../../components/HeaderComponents/Header2";
import Header3 from "../../components/HeaderComponents/Header3";

function Login() {
    const [email, setEmail] = useState("");
    const [password, setPassword] = useState("");
    const [emailError, setEmailError] = useState("");
    const [passwordError, setPasswordError] = useState("");
    const [showPassword, setShowPassword] = useState(false);
    const navigate = useNavigate();

    const redirect = () => {
        navigate('/');
    }

    const togglePasswordVisibility = () => {
        setShowPassword(!showPassword);
    }

    const loginFail = () => {
        Swal.fire({
            title : "Unable to Login",
            text : "Fill the valid details",
            icon : "error"
        })
    }

    const handleEmailChange = (e) => {
        setEmail(e.target.value);
        if(!email){
            setEmailError("");
        }
        else{
            setEmailError("");
        }
    };

    const handlePasswordChange = (e) =>{
        setPassword(e.target.value);
        if(!password){
            setPasswordError("");
        }
        else{
            setPasswordError("");
        }
    };

    const validateForm = () =>{
        let isValid = true;
        
        if(!email){
            setEmailError("Email is required");
            isValid = false;
        } else if(!email.endsWith("@nucleusteq.com")){
            setEmailError("Email must be in the form of @nucleusteq.com domain");
            isValid = false;
        } else {
            setEmailError("");
        }

        if (!password) {
            setPasswordError("Password is required ");
            isValid = false;
        }
        return isValid;
    };

    const handleFormSubmit = async (event) => {
        event.preventDefault();  
        if(!validateForm()){
            loginFail();
            return;
        }
            
        try {

            const data = {
                email,
                password,
            }

            const response = await UserServices.loginUser(data);
            localStorage.setItem("role", response.data.body.userRole);
            localStorage.setItem("id", response.data.body.userId)
            localStorage.setItem("name", response.data.body.name);
            localStorage.setItem("email", response.data.body.email);

            const userRole = localStorage.getItem("role");
                if (userRole === "ADMIN") {
                    SweetAlert.loginSuccessSwal("Logging in");
                    setTimeout(() => {
                        navigate("/admin");
                    }, 1500);
                } else {
                    SweetAlert.loginSuccessSwal("Logging in");
                    setTimeout(() => {
                        navigate("/user");
                    }, 1500);
                }

        } catch (err) {
                SweetAlert.alertError(err?.response?.data?.message);        
        }
    }

    return(
        <div className="registration-form">
            <Header2 className = "h2-heading" text = "Login" />

            <form onSubmit={handleFormSubmit}>
        
                <div className="form-group">
                    <InputComponent
                    className = "reg-input-fields"
                    type="email"
                    name="email"
                    placeholder="Enter your Email"
                    value={email}
                    onChange={handleEmailChange}
                    />
                    {emailError && <div className="error">{emailError}</div>}
                </div>

                <div className="form-group">
                    <div className="password-input-container">
                        
                        <InputComponent
                        className = "reg-input-fields"
                        type={showPassword ? "text" : "password"}
                        name="password"
                        placeholder="Enter your Password"
                        value={password}
                        onChange={handlePasswordChange}
                        />

                        <ButtonComponent 
                        type = "button"
                        className = "password-toggle-button"
                        onClick={togglePasswordVisibility}
                        text = {showPassword ? <FaEyeSlash/> : <FaEye/>} 
                        />

                    </div>
                    {passwordError && <div className="error">{passwordError}</div>}
                </div>

                <div className="button-container">
                    <InputComponent type="Submit" className="button-submit-login" value = "Login" />
                    <InputComponent type="button" className="button-button-login" value = "Home" onClick={redirect} />
                </div>

                <Header3 className = "h3-heading" text = "New to our Platform? " to = "/register" link = "Register Now"/>
            </form>
        </div>
    );
}

export default Login;