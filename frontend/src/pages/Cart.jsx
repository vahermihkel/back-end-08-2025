import { useState } from "react"

// rfce
function Cart() {
  const [products, setProducts] = useState(JSON.parse(localStorage.getItem("cart")) || []);

  function removeProduct(index) {
    products.splice(index,1); // update
    setProducts(products.slice()); // HTMLi uuendus
    localStorage.setItem("cart", JSON.stringify(products)); // LS uuendus
  }

  function empty() {
    setProducts([]); // HTMLi uuendus
    localStorage.setItem("cart", JSON.stringify([])); // LS uuendus
    //localStorage.setItem("cart", "[]");
  }

  return (
    <div>
      <button onClick={() => empty()}>Tühjenda</button>
      {products.map((product, index) => 
        <div key={index}>
          <img className="icon" src={product.image} alt="" />
          <div>{product.name}</div>
          <div>{product.price} €</div>
          <button onClick={() => removeProduct(index)}>x</button>
        </div>
      )}
    </div>
  )
}

export default Cart