import React from "react";

const SignOutIcon = (props) => {
  return (
    <div className="navbar-link">
    <button className={props.className} onClick={props.onClick}>
      <span className="text">{props.text} </span>
      {props.icon}
    </button>
    </div>
  );
};

export default SignOutIcon;
