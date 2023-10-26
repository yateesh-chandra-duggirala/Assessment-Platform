import React from "react";

const InputComponent = (props) => {

    return(
        <div>
            <input 
            className={props.className}
            type = {props.type}
            name = {props.name}
            placeholder = {props.placeholder}
            value={props.value}
            onChange={props.onChange}
            onClick={props.onClick}
            />
        </div>
    );
}

export default InputComponent;