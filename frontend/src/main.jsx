import { StrictMode } from 'react'
import { createRoot } from 'react-dom/client'
import 'bootstrap/dist/css/bootstrap.min.css';
import 'leaflet/dist/leaflet.css';
import './index.css'
import './i18n';
import App from './App.jsx'
import { BrowserRouter } from 'react-router-dom'

// URLi vahetus / Navigeerimine / Routing
// 1. lisada node_modules kausta react-router-dom moodul (npm i react-router-dom)
// 2. importida BrowserRouter
// 3. Ümbritseda App BrowserRouteriga
// 4. App.jsx failis teha URLi ja failide vahelisi seoseid

createRoot(document.getElementById('root')).render(
  <StrictMode>
    <BrowserRouter>
      <App />
    </BrowserRouter>
  </StrictMode>,
)
