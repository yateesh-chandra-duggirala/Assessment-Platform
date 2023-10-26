import React from "react";

const LabelComponent = (props) => {

    return(
        <div>
            <label
            className = {props.className}
            >
                {props.text}
            </label>
        </div>
    );
}

export default LabelComponent;