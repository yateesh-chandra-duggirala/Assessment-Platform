import React from "react";

const Header1 = (props) => {

    return(
        <div>
            <h1
            className={props.className}
            >
                {props.text}
            </h1>
        </div>
    );
}

export default Header1;