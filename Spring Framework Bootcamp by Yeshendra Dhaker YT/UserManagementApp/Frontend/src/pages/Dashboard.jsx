import React, { useEffect, useState } from "react";
import authService from "../services/authService";
import "../styles/Dashboard.css";

const PasswordChangeModel = ({ isOpen, onClose, onSave }) => {
  const [currentPassword, setCurrentPassword] = useState("");
  const [newPassword, setNewPassword] = useState("");
  const [confirmPassword, setConfirmPassword] = useState("");

  const [error, setError] = useState("");

  const handleSave = async () => {
    //basic validation
    if (newPassword !== confirmPassword) {
      setError("Password do not match");
      return;
    }

    try {
      await authService.changePassword(
        currentPassword,
        newPassword,
        confirmPassword
      );
      onSave();
      onClose();
    } catch (error) {
      setError("failed to change the current password");
    }
  };

  if (!isOpen) return null;

  return (
    <div className="modal-overlay">
      <div className="modal-content">
        <h2>Change Password</h2>
        {error && <div className="error-message">{error} </div>}
        <div className="from-group">
          <label htmlFor=""> Current Password</label>
          <input
            type="password"
            value={currentPassword}
            onChange={(e) => setCurrentPassword(e.target.value)}
          />
        </div>
        <div className="from-group">
          <label htmlFor=""> New Password</label>
          <input
            type="password"
            value={newPassword}
            onChange={(e) => setNewPassword(e.target.value)}
          />
        </div>
        <div className="from-group">
          <label htmlFor=""> Confirm Password</label>
          <input
            type="password"
            value={confirmPassword}
            onChange={(e) => setConfirmPassword(e.target.value)}
          />
        </div>
        <div className="modal-actions">
          <button className="btn btn-secondry" onClick={onClose}>
            CANCEL
          </button>
          <button className="btn btn-primary" onClick={handleSave}>
            SAVE
          </button>
        </div>
      </div>
    </div>
  );
};

const UserTable = () => {
  const [allUsers, setAllUsers] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);

  useEffect(() => {
    const fetchAllUsers = async () => {
      try {
        const response = await authService.getAllUsers();
        setAllUsers(response);
        setLoading(false);
      } catch (error) {
        setError("failed to get users");
        setLoading(false);
      }
    };
    fetchAllUsers();
  }, []);

  if (loading) return <div>loading users...</div>;
  if (error) return <div className="error-message">{error}</div>;

  const handleDeleteUser = async (userId) => {
    try {
      await authService.deleteUser(userId);
      setAllUsers(allUsers.filter((user) => user.id !== userId));
    } catch (error) {
      console.error("failed to delete the user");
    }
  };

  return (
    <div className="users-table-container">
      <h3>Manage All Users</h3>
      <table className="users-table">
        <thead>
          <tr>
            <th>ID</th>
            <th>Username</th>
            <th>Email</th>
            <th>Action</th>
          </tr>
        </thead>
        <tbody>
          {allUsers.map((user) => (
            <tr key={user.id}>
              <td>{user.id}</td>
              <td>{user.username}</td>
              <td>{user.email}</td>
              <td>
                <button
                  className="btn btn-danger"
                  onClick={() => handleDeleteUser(user.id)}
                >
                  DELETE
                </button>
              </td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
};

const Dashboard = () => {
  const [user, setUser] = useState(null);
  const [activeSection, setActiveSection] = useState("home");
  const [loading, setLoading] = useState(true);

  //admin
  const [isAdmin, setIsAdmin] = useState(false);

  //Profile editing status
  const [isEditing, setIsEditing] = useState(false);
  const [editedUser, setEditedUser] = useState({});

  //Password
  const [isPasswordModalOpen, setIsPasswordModalOpen] = useState(false);

  useEffect(() => {
    const fetchUserData = async () => {
      try {
        const currentUser = authService.getCurrentUser();
        setUser(currentUser);
        setEditedUser(currentUser);

        //check if the user role is admin or user

        const userRoles = currentUser.roles || [];
        setIsAdmin(userRoles.includes("ROLE_ADMIN"));
      } catch (error) {
        console.error("Error fetching user data", error);
      } finally {
        setLoading(false);
      }
    };
    fetchUserData();
  }, []);

  const handleEditToggle = () => {
    setIsEditing(!isEditing);
  };

  const handleInputChange = () => {
    const { name, value } = e.target;
    setEditedUser((prev) => ({
      ...prev,
      [name]: value,
    }));
  };

  const handleSavedProfile = async () => {
    try {
      await authService.updateProfile(editedUser);
      setUser(editedUser);
    } catch (error) {
      console.error("failed to update", error);
    }
  };

  const handleCancelEdit = () => {
    setEditedUser(user);
    setIsEditing(false);
  };

  if (loading) {
    return <div className="loading-spinner">loading...</div>;
  }

  return (
    <div className="dashboard-container">
      <div className="dashboard-sidebar">
        <div
          className={`dashboard-menu-item ${
            activeSection === "home" ? "active" : ""
          }`}
          onClick={() => setActiveSection("home")}
        >
          HOME
        </div>

        <div
          className={`dashboard-menu-item ${
            activeSection === "profile" ? "active" : ""
          }`}
          onClick={() => setActiveSection("profile")}
        >
          PROFILE
        </div>
        <div
          className={`dashboard-menu-item ${
            activeSection === "settings" ? "active" : ""
          }`}
          onClick={() => setActiveSection("settings")}
        >
          SETTINGS
        </div>
        {isAdmin && (
          <div
            className={`dashboard-menu-item ${
              activeSection === "users" ? "active" : ""
            }`}
            onClick={() => setActiveSection("users")}
          >
            USER
          </div>
        )}
      </div>
      <div className="dashboard-content">
        {activeSection === "home" && (
          <div className="dashboard-home">
            <h2>Welcome, {user.username}</h2>
            <p>
              This is your personal dashboard where you can manage your account,
              update your profile, and—if you're an admin—oversee all user
              activities.
            </p>
          </div>
        )}

        {activeSection === "profile" && (
          <div className="dashboard-profile">
            <h2>User Profile Information</h2>
            <div className="profile-details">
              <div className="profile-field">
                <label htmlFor="">Username:</label>
                <input
                  type="text"
                  name="username"
                  value={isEditing ? editedUser.username : user.username}
                  onChange={handleInputChange}
                  readOnly={!isEditing}
                />
              </div>
              <div className="profile-field">
                <label htmlFor="">Email:</label>
                <input
                  type="email"
                  name="email"
                  value={isEditing ? editedUser.email : user.email}
                  onChange={handleInputChange}
                  readOnly={!isEditing}
                />
              </div>
              <div className="profile-actions">
                {!isEditing ? (
                  <>
                    <button
                      className="btn btn-primary"
                      onClick={handleEditToggle}
                    >
                      EDIT
                    </button>
                    <button
                      className="btn btn-secondry"
                      onClick={() => setIsPasswordModalOpen(true)}
                    >
                      {" "}
                      CHANGE PASSWORD
                    </button>
                  </>
                ) : (
                  <>
                    <button
                      className="btn btn-primary"
                      onClick={handleSavedProfile}
                    >
                      SAVE
                    </button>
                    <button
                      className="btn btn-secondary"
                      onClick={handleCancelEdit}
                    >
                      CANCEL
                    </button>
                  </>
                )}
              </div>
            </div>
          </div>
        )}
        {activeSection === "settings" && (
          <div className="dashboard-setting">
            <h2>Settings</h2>
            <p>
              Customize your experience and manage account-related settings
              here.
            </p>
          </div>
        )}
        {activeSection === "users" && isAdmin && <UserTable />}
      </div>
      <PasswordChangeModel
        isOpen={isPasswordModalOpen}
        onClose={() => setIsPasswordModalOpen(false)}
        onSave={() => {
          console.log("Password changed successfully");
        }}
      />
    </div>
  );
};

export default Dashboard;
