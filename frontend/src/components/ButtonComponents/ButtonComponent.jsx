import React from "react";

const ButtonComponent = (props) => {

    return (
        <div>
            <button
                onClick={props.onClick}
                type={props.type}
                className={props.className}
                value={props.value}
                icon = {props.value}
            >
                {props.text}
            </button>
        </div>
    );
}

export default ButtonComponent;