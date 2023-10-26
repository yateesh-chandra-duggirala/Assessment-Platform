import React from "react";
import { Link } from "react-router-dom";

const Header3 = (props) => {

    return(
        <div>
            <h3
            className={props.className}
            >
                {props.text}
                <Link to = {props.to}>{props.link}</Link>
            </h3>
        </div>
    );
}

export default Header3;