import axios from "axios";

const BASE_URL = "https://spring-react-projects-71aa.onrender.com";

const api = axios.create({
  baseURL: BASE_URL,
});

// POST: Add an employee
export const addEmployee = (emp) => {
  return api.post("/addemp", emp);
};

// GET: Fetch all employees
export const getAllEmployees = () => {
  return api.get("/getallemps");
};

// Optional: export as default object if needed
export default {
  addEmployee,
  getAllEmployees,
};
