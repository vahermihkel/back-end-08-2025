import { useEffect, useState } from 'react'
import { ToastContainer, toast } from 'react-toastify';

function HomePage() {

  const [products, setProducts] = useState([]);
  const [categories, setCategories] = useState([]);

  useEffect(() => {
    showAllProducts();
  }, []);

  useEffect(() => {
    fetch("http://localhost:8080/categories")
      .then(res=>res.json())            
      .then(json=>setCategories(json))
  }, []);

  function showAllProducts() {
    fetch("http://localhost:8080/products")
      .then(res=>res.json())            
      .then(json=>setProducts(json))
  }

  function showProductsByCategory(categoryId) {
    fetch("http://localhost:8080/products-by-category?id=" + categoryId)
      .then(res=>res.json())            
      .then(json=>setProducts(json))
  }

  function addToCart(product) {
    toast.success("Toode edukalt ostukorvi lisatud " + product.name);
    const cartLS = JSON.parse(localStorage.getItem("cart")) || [];
    cartLS.push(product);
    localStorage.setItem("cart", JSON.stringify(cartLS));
  }

  return (
    <div>
      <button onClick={() => showAllProducts()}>Kõik kategooriad</button>
      {categories.map(category => 
        <button onClick={() => showProductsByCategory(category.id)} key={category.id}>
          {category.name}
        </button>)}

      <div>Kokku tooteid: {products.length} tk</div>

      <div className="products">
        {products.map(product => 
          <div className="product" key={product.id}>
            <img src={product.image} alt="" />
            <div>{product.id}</div>
            <div>{product.name}</div>
            <div>{product.price}</div>
            <button>Vt lähemalt</button>
            <button onClick={() => addToCart(product)}>Lisa ostukorvi</button>
          </div>)}
      </div>

      <ToastContainer
        position="bottom-right"
        autoClose={99999000}
        closeOnClick
        theme="colored"
      />
    </div>
  )
}

export default HomePage