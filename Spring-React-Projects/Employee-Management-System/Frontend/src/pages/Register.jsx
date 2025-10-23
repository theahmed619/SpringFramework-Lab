import React, { useState } from "react";
import { addEmployee } from "../service/api";

const Register = () => {
  // ✅ Define formData here at top level
  const [formData, setFormData] = useState({
    name: "",
    email: "",
    password: "",
  });

  // ✅ Handles input changes dynamically
  const handleOnChange = (e) => {
    const { name, value } = e.target;
    setFormData((prevState) => ({
      ...prevState,
      [name]: value,
    }));
  };

  // ✅ Form submit handler
  const handleSubmit = async (e) => {
    e.preventDefault();
    // ✅ Check if any field is empty
    const { name, email, password } = formData;
    if (!name.trim() || !email.trim() || !password.trim()) {
      alert("Please fill in all fields.");
      return; // stop submission
    }

    try {
      const response = await addEmployee(formData);
      console.log("Employee added successfully:", response.data);
      setFormData({ name: "", email: "", password: "" }); // reset form
    } catch (error) {
      console.log("Failed to add employee:", error);
    }
  };

  return (
    <div className="min-h-screen flex items-center justify-center bg-gray-100 px-4">
  <div className="bg-white p-6 sm:p-8 rounded-xl shadow-md w-full max-w-sm sm:max-w-md">
    <h2 className="text-xl sm:text-2xl font-bold mb-6 text-center text-gray-800">
      Add Employee
    </h2>
    <form onSubmit={handleSubmit} className="space-y-4">
      <input
        type="text"
        name="name"
        placeholder="Enter your name"
        value={formData.name}
        onChange={handleOnChange}
        className="w-full px-4 py-2 border border-gray-300 rounded-md focus:ring-2 focus:ring-blue-500"
      />
      <input
        type="email"
        name="email"
        placeholder="Enter your email"
        value={formData.email}
        onChange={handleOnChange}
        className="w-full px-4 py-2 border border-gray-300 rounded-md focus:ring-2 focus:ring-blue-500"
      />
      <input
        type="password"
        name="password"
        placeholder="Enter your password"
        value={formData.password}
        onChange={handleOnChange}
        className="w-full px-4 py-2 border border-gray-300 rounded-md focus:ring-2 focus:ring-blue-500"
      />
      <button
        type="submit"
        className="w-full bg-blue-600 text-white py-2 rounded-md hover:bg-blue-700 transition"
      >
        Submit
      </button>
    </form>
  </div>
</div>

  );
};

export default Register;
