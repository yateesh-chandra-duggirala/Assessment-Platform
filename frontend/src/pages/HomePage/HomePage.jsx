import React from "react";
import "./HomePage.css";
import { useNavigate } from "react-router-dom";
import ButtonComponent from "../../components/ButtonComponents/ButtonComponent";
import Header2 from "../../components/HeaderComponents/Header2";

function HomePage(){
    const navigate = useNavigate();
    const handleButton = () => {
        navigate("/login");
    }

    return(
        <div className="get-started">
            <header className="header">
                <h1>Welcome to the Assessment Platform</h1>
                <p>Get started to begin your learning journey!</p>
            </header>

            <main className="content">
                <section className="steps">
                    <div className="step">
                        <Header2 className = "h2" text = "Step 1"/>
                        <p>Sign up for an account</p>
                    </div>
                    <div className="step">
                        <Header2 className = "h2" text = "Step 2"/>
                        <p>Explore available assessments</p>
                    </div>
                    <div className="step">
                        <Header2 className = "h2" text = "Step 3"/>
                        <p>Take assessments and track your progress</p>
                    </div>
                </section>

                <section className="cta">
                    <Header2 className = "h2" text = "What are you yet waiting for?" />

                    <ButtonComponent
                    className = "get-started-button"
                    onClick = {handleButton}
                    text = "Get Started"
                    />
                </section>
            </main>
        </div>
  );
}

export default HomePage;