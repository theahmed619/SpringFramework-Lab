import axios from "axios";

//set your backend base url
const BASE_URL = "http://localhost:8089";

// create axios instance
const api = axios.create({
  baseURL: BASE_URL,
  headers: {
    "Content-Type": "application/json",
  },
  withCredentials: true, // Important fro handling cookies cross origin
});

// Response Intercepter for global error handling

api.interceptors.response.use(
  (response) => response,
  (error) => {
    // Global Error handling
    if (error.response) {
      switch (error.response.status) {
        case 401: //unauthorize
          // Redirect user to login or logout
          authService.logout();
          window.location.href = "/login";
          break;

        case 403: //Forebidden
          console.error("Access forbidden");
          break;

        case 404: //Resource not found
          console.error("Resource not found");
          break;

        case 500: //server error
          console.error("Internal server error");
          break;
      }
    } else if (error.request) {
      // request made but not response received

      console.error("No reponse recived", error.request);
    } else {
      // somthing happend in setting up the request
      console.error("Error  in setting request", error.message);
    }

    return Promise.reject(error);
  }
);

const authService = {
  //sign up method

  signupNormalUser: async (username, email, password) => {
    try {
      const response = await api.post("/auth/registernormaluser", {
        username,
        email,
        password,
      });
      return response.data;
    } catch (error) {
      console.error("Signup failed", error);
      throw error;
    }
  },

  //login method
  login: async (username, password) => {
    try {
      const response = await api.post("/auth/login", { username, password });
      //fetch current user
      const user = await authService.fetchCurrentUser();
      return {
        ...response.data,
        user,
      };
    } catch (error) {
      console.error("login failed", error);
      throw error;
    }
  },

  //fetch current user
  fetchCurrentUser: async () => {
    try {
      const response = await api.get("/auth/getcurrentuser");

      // store userDTO in localstorage for quick access
      localStorage.setItem("user", JSON.stringify(response.data));

      return response.data;
    } catch (error) {
      console.error("Fetching user data  failed", error);

      //if unauthorize
      if (error.response && error.response.status === 401) {
        authService.logout();
      }
      return null;
    }
  },

  // get current user from local storage
  getCurrentUser: () => {
    const user = localStorage.getItem("user");
    try {
      return user ? JSON.parse(user) : null;
    } catch (error) {
      console.error("Error parsing user data", error);
      return null;
    }
  },

  //logout method
  logout: async () => {
    try {
      await api.post("/auth/logout");

      //clear any localstorage or state
      localStorage.removeItem("user");
    } catch (error) {
      console.error("logout failed", error);
    }
  },

  isAuthenticated: async () => {
    try {
      //verify authentication by fetching current user
      const user = await authService.fetchCurrentUser();
      return !!user;
    } catch (error) {
      return false;
    }
  },

  updateProfile: async (userData) => {
    try {
      const response = await api.put(
        `/users/updateuser/${userData.id}`,
        userData
      );
      const currentUser = authService.getCurrentUser();
      const updatedUser = { ...currentUser, ...response.data };
      localStorage.setItem("user", JSON.stringify(updatedUser));
    } catch (error) {
      console.error("profile update failed", error);
      throw error;
    }
  },

  getAllUsers: async () => {
    try {
      const response = await api.get("/users/getallusers");
      return response.data;
    } catch (error) {
      console.error("failed  to  fetch all users");
      throw error;
    }
  },

  deleteUser: async (userId) => {
    try {
      const response = await api.delete(`/users/deleteuser/${userId}`);
      return response.data;
    } catch (error) {
      console.error("failed  to  delete user", error);
      throw error;
    }
  },

  changePassword: async (currentPassword, newPassword, confirmPassword) => {
    try {
      const currentUser = authService.getCurrentUser();
      if (!currentUser || !currentUser.id) {
        throw new Error("User not found");
      }

      const response = await api.put(
        `/users/changepassword/${currentUser.id}`,
        {
          currentPassword,
          newPassword,
          confirmPassword,
        }
      );
      return response.data;
    } catch (error) {
      console.error("failed to change the password", error);
      throw error;
    }
  },
};

export default authService;
