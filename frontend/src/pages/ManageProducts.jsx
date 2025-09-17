import { useEffect, useState } from 'react';
import Table from 'react-bootstrap/Table';

function ManageProducts() {
  const [products, setProducts] = useState([]);

  useEffect(() => {
    fetch("http://localhost:8080/products")
      .then(res => res.json())
      .then(json => setProducts(json))
  }, []);

  function deleteProduct(productId) {
    fetch("http://localhost:8080/products/" + productId, {method: "DELETE"})
      .then(res => res.json())
      .then(json => {
          if (json.message && json.timestamp && json.statusCode) {
            alert(json.message);
          } else {
            setProducts(json);
          }
        }
      )
  }

  return (
    <div>
      <Table striped bordered hover>
        <thead>
          <tr>
            <th>#</th>
            <th>Name</th>
            <th>Price</th>
            <th>Stock</th>
            <th>Active</th>
            <th>Description</th>
            <th>Image</th>
            <th>Delete</th>
          </tr>
        </thead>
        <tbody>
          {products.map(product => 
          <tr key={product.id}>
            <td>{product.id}</td>
            <td>{product.name}</td>
            <td>{product.price}</td>
            <td>{product.stock}</td>
            <td>{product.active}</td>
            <td>{product.description}</td>
            <td>{product.image}</td>
            <td><button onClick={() => deleteProduct(product.id)}>x</button></td>
          </tr>)}
        </tbody>
      </Table>
    </div>
  )
}

export default ManageProducts