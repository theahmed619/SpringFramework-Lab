import React, { useState, useEffect } from "react";
import {getAllEmployees} from "../service/api.js";

const Home = () => {
  const [employees, setEmployees] = useState([]);

  useEffect(() => {
    loadEmployees();
  }, []);

  const loadEmployees = async () => {
    try {
      const response = await getAllEmployees();
      setEmployees(response.data);
    } catch (error) {
      console.error("Failed to fetch employees:", error);
    }
  };

  return (
    <div className="min-h-screen bg-gray-100 px-4 py-6">
      <div className="max-w-5xl mx-auto">
        <h1 className="text-xl sm:text-2xl md:text-3xl font-bold text-center mb-6">
          Welcome to Employee Management System!!!
        </h1>

        <div className="bg-white shadow-md rounded-lg p-4">
          <h2 className="text-lg sm:text-xl font-semibold mb-4 text-center">
            Employee List
          </h2>

          <div className="overflow-x-auto">
            <table className="min-w-full border border-gray-300 text-sm sm:text-base">
              <thead className="bg-blue-600 text-white">
                <tr>
                  <th className="py-2 px-4 border">ID</th>
                  <th className="py-2 px-4 border">Name</th>
                  <th className="py-2 px-4 border">Email</th>
                  <th className="py-2 px-4 border">Password</th>
                </tr>
              </thead>
              <tbody>
                {employees.length > 0 ? (
                  employees.map((emp) => (
                    <tr key={emp.id} className="hover:bg-gray-100">
                      <td className="py-2 px-4 border text-center">{emp.id}</td>
                      <td className="py-2 px-4 border">{emp.name}</td>
                      <td className="py-2 px-4 border">{emp.email}</td>
                      <td className="py-2 px-4 border">{emp.password}</td>
                    </tr>
                  ))
                ) : (
                  <tr>
                    <td colSpan="4" className="text-center py-4 text-gray-500">
                      No employees found.
                    </td>
                  </tr>
                )}
              </tbody>
            </table>
          </div>
        </div>
      </div>
    </div>
  );
};

export default Home;
