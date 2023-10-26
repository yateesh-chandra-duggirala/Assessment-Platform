import React from "react";

const IconButton = ({ text, icon, onClick, className, iconLeft }) => {
  return (
    <div>
      <button className={className} onClick={onClick}>
        {iconLeft && icon} {text} {!iconLeft && icon}
      </button>
    </div>
  );
};

export default IconButton;
