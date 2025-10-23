import React, { useState } from "react";
import { Link } from "react-router-dom";

const Navbar = () => {
  const [menuOpen, setMenuOpen] = useState(false);

  return (
    <nav className="bg-blue-600 text-white shadow-md">
      <div className="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8">
        <div className="flex items-center justify-between h-16">
          <div className="text-xl font-bold">
            <Link to="/">EMP</Link>
          </div>

          {/* Desktop Links */}
          <div className="hidden md:flex space-x-6">
            <Link
              to="/register"
              className="hover:bg-blue-700 px-3 py-2 rounded-md"
            >
              Register
            </Link>
            <Link
              to="/login"
              className="hover:bg-blue-700 px-3 py-2 rounded-md"
            >
              Login
            </Link>
          </div>

          {/* Hamburger Menu - Mobile Only */}
          <div className="md:hidden">
            <button
              onClick={() => setMenuOpen(!menuOpen)}
              className="text-white focus:outline-none text-2xl"
            >
              ☰
            </button>
          </div>
        </div>

        {/* Mobile Menu */}
        {menuOpen && (
          <div className="md:hidden ">
            <div className="space-y-2 p-3">
              <Link
                to="/register"
                className="block px-4 py-2 bg-blue-500 rounded-md"
                onClick={() => setMenuOpen(false)}
              >
                Register
              </Link>
              <Link
                to="/login"
                className="block px-4 py-2 bg-blue-500 rounded-md  "
                onClick={() => setMenuOpen(false)}
              >
                Login
              </Link>
            </div>
          </div>
        )}
      </div>
    </nav>
  );
};

export default Navbar;
