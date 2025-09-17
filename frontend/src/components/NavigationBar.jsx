import Container from 'react-bootstrap/Container';
import Nav from 'react-bootstrap/Nav';
import Navbar from 'react-bootstrap/Navbar';
import NavDropdown from 'react-bootstrap/NavDropdown';
import { Link } from 'react-router-dom'
import { useTranslation } from 'react-i18next';
import english from "../assets/english.png";
import estonian from "../assets/estonian.png";

function NavigationBar() {
  const { t, i18n } = useTranslation();
  // return <h1>{t('Welcome to React')}</h1>

  function updateLanguage(newLang) {
    i18n.changeLanguage(newLang);
    localStorage.setItem("language", newLang);
  }

  return (
    <Navbar collapseOnSelect expand="lg" className="bg-body-tertiary">
      <Container>
        <Navbar.Brand as={Link} to="/">Webshop</Navbar.Brand>
        <Navbar.Toggle aria-controls="responsive-navbar-nav" />
        <Navbar.Collapse id="responsive-navbar-nav">
          <Nav className="me-auto">
            <Nav.Link as={Link} to="/ostukorv">{t("nav.cart")}</Nav.Link>
            <Nav.Link as={Link} to="/poed">{t("nav.shops")}</Nav.Link>
            <Nav.Link as={Link} to="/kontakteeru">{t("nav.contact")}</Nav.Link>
            <NavDropdown title="Admin" id="collapsible-nav-dropdown">
              
              <NavDropdown.Item as={Link} to="/lisa-toode">{t("nav.add-product")}</NavDropdown.Item>
              <NavDropdown.Item as={Link} to="/halda-tooteid">{t("nav.manage-products")}</NavDropdown.Item>
              
              <NavDropdown.Item as={Link} to="/">Something</NavDropdown.Item>
              <NavDropdown.Divider />
              <NavDropdown.Item as={Link} to="/">
                Separated link
              </NavDropdown.Item>
            </NavDropdown>
          </Nav>
          <Nav>
            <Nav.Link as={Link} to="/login">{t("nav.login")}</Nav.Link>
            <Nav.Link as={Link} to="/registreeru">{t("nav.signup")}</Nav.Link>
            <img className="icon" src={english} onClick={() => updateLanguage("en")} alt="" />
            <img className="icon" src={estonian} onClick={() => updateLanguage("et")} alt="" />
          </Nav>
        </Navbar.Collapse>
      </Container>
    </Navbar>
  );
}

export default NavigationBar;