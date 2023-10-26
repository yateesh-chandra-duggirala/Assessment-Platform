import React from "react";

const Header2 = (props) => {

    return(
        <div>
            <h2
            className={props.className}
            >
                {props.text}
            </h2>
        </div>
    );
}

export default Header2;