import React, { useEffect, useState } from "react";
import "./Categories.css";
import { useNavigate, useParams } from "react-router-dom";
import AdminNavBar from "../../components/AdminNavBar";
import NotFound from "../../components/NotFound";
import CategoryService from "../../services/CategoryService";
import SweetAlert from "../../components/SweetAlerts/SweetAlert";
import InputComponent from "../../components/InputComponent";

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
        .catch((error) => {
            console.error("Error fetching category data: ", error);
        });
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
        } else {
            setCategoryNameError("");
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
      
        try {
            if (isUpdating) {
                try{
                    await CategoryService.updateCategory(categoryId, categoryData);
                    SweetAlert.successAlert("Updated");
                    console.log("Category updated successfully.");
                    navigate("/manage-category");
                }

                catch(error){
                    if(error?.response?.data?.message === "Category already Exists"){
                        setCategoryNameError("Category already exists");
                        SweetAlert.alreadyExists("Category")
                    }
                    console.log(error);  
                }

            } else {

                try{
                    await CategoryService.addCategory(categoryData);
                    SweetAlert.successAlert("Added");
                    console.log("Category added successfully.");
                    navigate("/manage-category")
              } catch(error) {
                    console.error(error)
                    if(error?.response?.data?.message === "Category already Exists"){
                        setCategoryNameError("Category already exists")
                        SweetAlert.alreadyExists("Category");
                    }
                }
            }

        } catch (error) {
            console.error("Error:", error);
        }
    };
    
    return (
        <div className="App">
            {userRole === "ADMIN" || userRole === "USER" ? (
            <>
                <AdminNavBar/>
                <div className="add-category-container">
                    <h1>{isUpdating ? 'Update Category' : 'Add Category'}</h1>
                    <form onSubmit={handleAddOrUpdateCategory}>
                        <div className="form-group">
                            <label>Category Title:</label>
                            <InputComponent
                              type="text"
                              value={categoryName}
                              onChange={handleCategoryNameChange}
                              placeholder="Enter Category Name"
                            />
                            {categoryNameError && <div className="error">{categoryNameError}</div>}
                        </div>
                      
                        <div className="form-group">
                            <label>Description:</label>
                            <textarea
                              value={description}
                              onChange={handleCategoryDescriptionChange}
                              placeholder="Enter Category Description"
                            />
                            {categoryDescriptionError && <div className="error">{categoryDescriptionError}</div>}
                        </div>

                        <div className="form-group">
                            <div className="button-container-category">
                                <button type = "Submit">{isUpdating ? 'Update' : 'Add'}</button>
                                <button type = "button" className="red-button" onClick={()=> navigate("/manage-category")}>Cancel</button>
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