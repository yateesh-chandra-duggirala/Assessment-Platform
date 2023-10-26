import React from 'react';

function SelectCorrectOption({ isEditingQuestion, editedQuestion, newQuestion, handleInputChange, questiontype }) {
  
  const isMCQType = questiontype === "MCQ";
  
  return (
    <select
      name="correctOption"
      className="question-form-group select"
      placeholder="Correct Option"
      value={isEditingQuestion ? editedQuestion.correctOption : newQuestion.correctOption}
      onChange={handleInputChange}
    >
      <option value="">Select Correct Option</option>
      <option value={isEditingQuestion ? editedQuestion.option1 : newQuestion.option1}>
        {isEditingQuestion ? editedQuestion.option1 : newQuestion.option1}
      </option>
      <option value={isEditingQuestion ? editedQuestion.option2 : newQuestion.option2}>
        {isEditingQuestion ? editedQuestion.option2 : newQuestion.option2}
      </option>

      {isMCQType && (
        <>
          <option value={isEditingQuestion ? editedQuestion.option3 : newQuestion.option3}>
            {isEditingQuestion ? editedQuestion.option3 : newQuestion.option3}
          </option>
          <option value={isEditingQuestion ? editedQuestion.option4 : newQuestion.option4}>
            {isEditingQuestion ? editedQuestion.option4 : newQuestion.option4}
          </option>
        </>
      )}
    </select>
  );
}

export default SelectCorrectOption;