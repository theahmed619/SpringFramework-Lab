import { useState } from "react";
import { useNavigate, Link } from "react-router-dom";

import authService from "../services/authService";
import "../styles/Signup.css";

const Signup = () => {
  const [error, setError] = useState("");
  const [username, setUsername] = useState("");
  const [email, setEmail] = useState("");
  const [password, setPasword] = useState("");
  const [confirmPassword, setConfirmPasword] = useState("");
  const navigate = useNavigate();
  const handleSignup = async (e) => {
    e.preventDefault();

    if (password !== confirmPassword) {
      setError("Password do not match");
      return;
    }

    try {
      await authService.signupNormalUser(username, email, password);
      navigate("/login");
    } catch (error) {
      setError("Signup Failed");
      console.error("Signup error", error);
    }
  };

  return (
    <div className="signup-container">
      <div className="signup-form">
        <h2>Sign Up </h2>
        {error && <div className="error-message">{error}</div>}

        <form onSubmit={handleSignup}>
          <div className="form-group">
            <label htmlFor="username">Username</label>
            <input
              type="text"
              id="username"
              value={username}
              onChange={(e) => setUsername(e.target.value)}
              required
            />
          </div>
          <div className="form-group">
            <label htmlFor="email">Email</label>
            <input
              type="email"
              id="email"
              value={email}
              onChange={(e) => setEmail(e.target.value)}
              required
            />
          </div>
          <div className="form-group">
            <label htmlFor="password">Password</label>
            <input
              type="password"
              id="password"
              value={password}
              onChange={(e) => setPasword(e.target.value)}
              required
            />
          </div>

          <div className="form-group">
            <label htmlFor="confirm-password">Password</label>
            <input
              type="password"
              id="confirm-password"
              value={confirmPassword}
              onChange={(e) => setConfirmPasword(e.target.value)}
              required
            />
          </div>

          <button type="submit" className="signup-button">
            Signup
          </button>
          <p>
            Already Have an account <Link to="/login">Login</Link>
          </p>
        </form>
      </div>
    </div>
  );
};

export default Signup;
