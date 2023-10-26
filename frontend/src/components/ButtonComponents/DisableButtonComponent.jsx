import React from "react";

const DisableButtonComponent = (props) => {
    return (
        <div>
            <button
                onClick={props.onClick}
                type={props.type}
                className={props.className}
                value={props.value}
                icon = {props.value}
                disabled = {props.disabled}
            >
                {props.text}
            </button>
        </div>
    );
}

export default DisableButtonComponent;