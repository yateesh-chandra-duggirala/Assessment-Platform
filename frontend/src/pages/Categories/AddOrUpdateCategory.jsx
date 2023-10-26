import React, { useEffect, useState } from "react";
import "./Categories.css";
import { useNavigate, useParams } from "react-router-dom";
import AdminNavBar from "../../components/NavBars/AdminNavBar";
import NotFound from "../../pages/HomePage/NotFound";
import CategoryService from "../../services/CategoryService";
import SweetAlert from "../../components/SweetAlerts/SweetAlert";
import InputComponent from "../../components/FormElements/InputComponent";
import LabelComponent from "../../components/FormElements/LabelComponent";
import TextAreaComponent from "../../components/FormElements/TextAreaComponent";
import Header1 from "../../components/HeaderComponents/Header1";
import ButtonComponent from "../../components/ButtonComponents/ButtonComponent";

const AddOrUpdateCategory = () =>{

    const { categoryId } = useParams();
    const navigate = useNavigate();
    const userRole = localStorage.getItem("role");
    const isUpdating = categoryId !== undefined;

    const [categoryName, setCategoryName] = useState("");
    const [description, setDescription] = useState("");

    const [categoryNameError, setCategoryNameError] = useState("");
    const [categoryDescriptionError, setCategoryDescriptionError] = useState("");

    
    const fetchCategoryData = () => {
        CategoryService.getCategoryById(categoryId)
        .then((response) => {
            const { categoryName, description } = response.data.body;
            setCategoryName(categoryName);
            setDescription(description);
        })
    };

    useEffect(() => {
        if (isUpdating) {
            fetchCategoryData();
        }
    }, []);

    const handleCategoryNameChange = (e) => {
        const validName = e.target.value;
        setCategoryName(validName);
        if (!validName) {
            setCategoryNameError("Category name is required");
        } else {
            setCategoryNameError("");
        }
    };

    const handleCategoryDescriptionChange = (e) => {
        const validDescription = e.target.value;
        setDescription(validDescription);
        if (!validDescription) {
            setCategoryDescriptionError("Category description is required");
        } else {
            setCategoryDescriptionError("");
        }
    };

    const validForm = () => {
        let isValid = true;
        if (!categoryName) {
            setCategoryNameError("Category Title is Required");
            isValid = false;
        } else if(categoryName.startsWith(" ")){
            setCategoryNameError("Name should start with Spaces.");
            isValid = false;
        } else {
            setCategoryNameError('');
        }

        if (!description) {
            setCategoryDescriptionError("Add some description");
            isValid = false;
        } else {
            setCategoryDescriptionError("");
        }
        return isValid;
    };

    const handleAddOrUpdateCategory = async (e) => {
        e.preventDefault();

        if (!validForm()) {
            SweetAlert.missingField();
            return;
        }
        
        const categoryData = {
            categoryName,
            description,
        };
      
        if (isUpdating) {
            try{
                await CategoryService.updateCategory(categoryId, categoryData);
                SweetAlert.successAlert("Updated");
                navigate("/manage-category");
            }

            catch(error){
                if(error?.response?.data?.message === "Category already Exists"){
                    setCategoryNameError("Category already exists");
                    SweetAlert.alreadyExists("Category")
                }
            }

        } else {
            try{
                await CategoryService.addCategory(categoryData);
                SweetAlert.successAlert("Added");
                navigate("/manage-category")
            } catch(error) {
                if(error?.response?.data?.message === "Category already Exists"){
                    setCategoryNameError("Category already exists")
                    SweetAlert.alreadyExists("Category");
                }
            }
        }
    };
    
    return (
        <div className="App">
            {userRole === "ADMIN" ? (
            <>
                <AdminNavBar/>
                <div className="add-category-container">
                    <Header1 className = "arial" text = {isUpdating ? 'Update Category' : 'Add Category'} />
                    <form onSubmit={handleAddOrUpdateCategory}>
                        <div className="form-group">                     
                            <LabelComponent className = "label" text = "Category Title : "/>
                            <InputComponent
                              type="text"
                              value={categoryName}
                              onChange={handleCategoryNameChange}
                              placeholder="Enter Category Name"
                            />
                            {categoryNameError && <div className="error">{categoryNameError}</div>}
                        </div>
                      
                        <div className="form-group">
                            <LabelComponent className = "label" text = "Description :"/>
                            <TextAreaComponent
                              className = "textarea"
                              value={description}
                              onChange={handleCategoryDescriptionChange}
                              placeholder="Enter Category Description"
                            />
                            {categoryDescriptionError && <div className="error">{categoryDescriptionError}</div>}
                        </div>

                        <div className="form-group">
                            <div className="button-container-category">
                                <ButtonComponent className = "blue-button button" type = "Submit" text = {isUpdating ? 'Update' : 'Add'} />
                                <ButtonComponent className = "red-button button" type = "button" text = "Cancel" onClick={()=> navigate("/manage-category")} />
                            </div>
                        </div>
                    </form>
                </div>
            </>
            ) : (
            
            <div>
              <NotFound/>
            </div>
            
            )}
        </div>
    );
}

export default AddOrUpdateCategory;