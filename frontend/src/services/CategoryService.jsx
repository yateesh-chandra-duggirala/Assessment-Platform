import axios from "axios";

const CATEGORY_URL = 'http://localhost:8082/category';

class CategoryService{
    
    addCategory(Category){
        return axios.post(CATEGORY_URL, Category);
    }

    getCategories(){
        return axios.get(CATEGORY_URL);
    }

    getCategoryById(id){
        return axios.get(CATEGORY_URL+ '/'+ id);
    }

    updateCategory(id, Category){
        return axios.put(CATEGORY_URL+ '/'+id, Category);
    }

    deleteCategory(id){
        return axios.delete(CATEGORY_URL+'/'+id);
    }
}

export default new CategoryService();