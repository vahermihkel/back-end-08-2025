import './App.css'
import { Route, Routes } from 'react-router-dom'
import HomePage from './pages/HomePage';
import AddProduct from './pages/AddProduct';
import ManageProducts from './pages/ManageProducts';
import NavigationBar from './components/NavigationBar';
import Cart from './pages/Cart';
import ContactUs from './pages/ContactUs';
import Login from './pages/Login';
import Signup from './pages/Signup';
import Shops from './pages/Shops';
import { useState } from 'react';

function App() {
  const [darkMode, setDarkMode] = useState(localStorage.getItem("isDarkTheme") === "true");

  function updateMode(isDark) {
    setDarkMode(isDark);
    localStorage.setItem("isDarkTheme", isDark);
  } 

  return (
    <div className={darkMode ? "dark-mode" : undefined}>
      <NavigationBar />
      <button onClick={() => updateMode(true)}>Dark mode</button>
      <button onClick={() => updateMode(false)}>Light mode</button>

      <Routes>
        <Route path='/' element={ <HomePage /> } />
        <Route path='/lisa-toode' element={ <AddProduct /> } />
        <Route path='/halda-tooteid' element={ <ManageProducts /> } />
        <Route path='/ostukorv' element={ <Cart /> } />
        <Route path='/kontakteeru' element={ <ContactUs /> } />
        <Route path='/login' element={ <Login /> } />
        <Route path='/registreeru' element={ <Signup /> } />
        <Route path='/poed' element={ <Shops /> } />
      </Routes>

    {/*
    self-closing elements, kellel pole algus ja lõpu elementi
    <br />
    <img src="" alt="" />
    <input type="text" />
    <Route path='/'  /> */}
    </div>
  )
}

export default App
