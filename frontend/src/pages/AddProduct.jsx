import { Button, Checkbox, FormControlLabel, TextField } from "@mui/material";
import { useState } from "react";
import { ToastContainer, toast } from 'react-toastify';

function AddProduct() {
  const [product, setProduct] = useState({active: true, image: "http://"});

  function add() {
    // pole sisestama hakanudki    VÕI   sisestas aga kustutas kõik ära
    if (product.name === undefined || product.name === "") {
      toast.error("Tühja nimega ei saa lisada");
      return; // <--- ära edasi mine
    }

    if (product.price === undefined || product.price <= 0) {
      toast.error("Hind peab olema positiivne täisarv");
      return; // <--- ära edasi mine
    }

    fetch("http://localhost:8080/products", {
      method: "POST", 
      body: JSON.stringify(product),
      headers: {
        "Content-Type": "application/json"
      }
    }).then(res => res.json())
      .then(() => toast.success("Toode edukalt lisatud!"));
  }

  return (
    <div>
      <div>Ajutine väljanäitamine: {JSON.stringify(product)}</div>
      {/* <label>Toote nimi</label> <br />
      <input  type="text" /> <br /> */}
      <br />
      <TextField label="Toote nimi" onChange={(e) => setProduct({...product, name: e.target.value})} variant="outlined" />
      <br /><br />
      <TextField label="Toote hind" onChange={(e) => setProduct({...product, price: Number(e.target.value)})} variant="outlined" />
      <br /><br />
      <TextField label="Toote kogus" onChange={(e) => setProduct({...product, stock: Number(e.target.value)})} variant="outlined" />
      <br /><br />
      {/* <TextField label="Toote aktiivsus"  variant="outlined" /> */}
      <FormControlLabel label="Toode aktiivne" control={<Checkbox defaultChecked onChange={(e) => setProduct({...product, active: e.target.checked})} />} />
      <br /><br />
      <TextField label="Toote kirjeldus" onChange={(e) => setProduct({...product, description: e.target.value})} variant="outlined" />
      <br /><br />
      <TextField label="Toote pilt" defaultValue={"http://"} onChange={(e) => setProduct({...product, image: e.target.value})} variant="outlined" />
      <br /><br />
      {/* <label>Toote hind</label> <br />
      <input onChange={(e) => setProduct({...product, price: Number(e.target.value)})} type="number" /> <br />
      <label>Toote kogus</label> <br />
      <input onChange={(e) => setProduct({...product, stock: Number(e.target.value)})} type="number" /> <br />
      <label>Toote aktiivsus</label> <br />
      <input onChange={(e) => setProduct({...product, active: e.target.checked})} type="checkbox" /> <br />
      <label>Toote kirjeldus</label> <br />
      <input onChange={(e) => setProduct({...product, description: e.target.value})} type="text" /> <br />
      <label>Toote pilt</label> <br />
      <input onChange={(e) => setProduct({...product, image: e.target.value})} type="text" /> <br /> */}
      <Button variant="contained" onClick={add}>Sisesta</Button>

      <ToastContainer
        position="bottom-right"
        autoClose={99999000}
        closeOnClick
        theme="colored"
      />
    </div>
  )
}

export default AddProduct