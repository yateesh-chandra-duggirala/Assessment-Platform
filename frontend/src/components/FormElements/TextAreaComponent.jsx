import React from "react";

const TextAreaComponent = (props) => {

    return(
        <div>
            <textarea
            className={props.className}
            name = {props.name}
            placeholder = {props.placeholder}
            value={props.value}
            onChange={props.onChange}
            onClick={props.onClick}
            />
        </div>
    );
}

export default TextAreaComponent;