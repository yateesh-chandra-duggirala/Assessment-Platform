import axios from "axios";
import React, { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";

const AdminCategory = () => {
    const [categories, setCategories] = useState([]);
    const [categoryName, setCategoryName] = useState('');
    const [description, setDescription] = useState('');
    const [selectedCategoryId, setSelectedCategoryId] = useState(null);
    const navigate = useNavigate();

    useEffect(() => {
        fetchCategories();
    }, []);

    const fetchCategories = () => {
        axios.get('http://localhost:8082/api/v1/category').then(response => setCategories(response.data))
        .catch(error => console.error("Error fetching Categories", error));
    };

    const handleAddCategory = () => {
        const newCategory = {
            categoryName,
            description,
        };

        axios.post('http://localhost:8082/api/v1/category', newCategory)
        .then(response => {
            console.log('Category added : ', response.data);
            fetchCategories();
            setCategoryName('');
            setDescription('')
        })
        .catch(error => console.error('Error adding category', error));
    };

    const handleUpdateCategory = () => {
        if(!selectedCategoryId) return;

        const updatedCategory = {
            categoryName,
            description,
        };

        axios.put(`http://localhost:8082/api/v1/category/${selectedCategoryId}`, updatedCategory)
        .then(response => {
            console.log('Category Updated : ', response.date);
            fetchCategories();
            setCategoryName('');
            setDescription('');
            setSelectedCategoryId(null);
        })
        .catch(error => console.error('Error updating category', error));
    };

    const handleDeleteCategory = (categoryId) => {
        axios.delete(`http://localhost:8082/api/v1/category/${categoryId}`)
        .then(() => {
            console.log('Category deleted', categoryId);
            fetchCategories();
        })
        .catch(error => console.error('Error deleting category', error));
    };

    return(
        <div className = 'wrapper-white'>
            <h1>Admin Category</h1>
            <div>
                <h3>Add or update category</h3>
                <table>
                    <thead>
                        <th>Category name</th>
                        <th>Description</th>
                        <th>Actions</th>
                    </thead>
                    <tbody>
                        <tr>
                            <td>Java</td>
                            <td>This is Java category</td>
                        </tr>
                        <tr></tr>

                    </tbody>
                </table>
                <input type="text"
                placeholder="Category Name"
                value = {categoryName}
                onChange={ e => setCategoryName(e.target.value)}
                />

                <textarea
                placeholder="Category Description"
                value = {description}
                onChange={ e => setDescription(e.target.value)}
                />

                <button onClick={selectedCategoryId ? handleUpdateCategory : handleAddCategory}>
                    {selectedCategoryId ? 'Update Category' : 'Add Category'}
                </button>

            </div>

            <div>
                <h3> Delete Category </h3>
                <select value = {selectedCategoryId} onChange={e => setSelectedCategoryId(e.target.value)}>
                    <option value = {null}>Select a category</option>
                    {categories.map(category => (
                        <option key = {category.categoryId} value = {category.categoryId}>
                            {category.categoryName}
                        </option>
                    ))}
                </select>
                
                <div className="input-box button">
                    <button onClick={() => handleDeleteCategory (selectedCategoryId)}>
                        Delete category
                    </button>
                    <input type="button" value="View categories" onClick={() => navigate("/view")}/>
                </div>
            </div>
        </div>
    );
};

export default AdminCategory;