import React from "react";
import authService from "../services/authService";
import { Link, Navigate, useNavigate } from "react-router-dom";
import "../styles/Navbar.css";

const Navbar = () => {
  const navigate = useNavigate();
  const currentUser = authService.getCurrentUser();

  const handleLogout = async () => {
    try {
      authService.logout();
      navigate("/login");
    } catch (error) {
      console.log("Logout Failed", error);
    }
  };
  return (
    <nav className="navbar">
      <Link to="/" className="navbar-brand">
        User Management
      </Link>
      <div className="navbar-links">
        {currentUser ? (
          <>
            <span className="navbar-user">Welcome, {currentUser.username}</span>
            <Link to="/dashboard" className="navbar-link">
              Dashboard
            </Link>
            <button onClick={handleLogout} className="logout-button">
              Logout
            </button>
          </>
        ) : (
          <>
            <Link to="/login" className="navbra-link">
              Login
            </Link>
            <Link to="/signup" className="navbra-link">
              signup
            </Link>
          </>
        )}
      </div>
    </nav>
  );
};

export default Navbar;
