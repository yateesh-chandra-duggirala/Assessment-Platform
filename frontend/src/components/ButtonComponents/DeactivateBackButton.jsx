import React, { useEffect } from "react";
import { useLocation, useParams } from 'react-router-dom';

const DeactivateBackButton = () => {
    const location = useLocation();
    const { quizId } = useParams();

    useEffect(() => {

        if (location.pathname === `/test/${quizId}` || location.pathname === `/user` || location.pathname === `/admin` || location.pathname === `/profile` || location.pathname === `/manage-category`) {
            window.history.pushState(null, '', window.location.href);
            window.onpopstate = () => {
                window.history.pushState(null, '', window.location.href);
            }
        }
    }, [location.pathname]);
}

export default DeactivateBackButton;
