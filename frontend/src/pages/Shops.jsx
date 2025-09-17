import { MapContainer, Marker, Popup, TileLayer } from 'react-leaflet'

function Shops() {
  return (
    <div>
      <MapContainer style={{height: "300px"}} center={[59.436, 24.744]} zoom={11} scrollWheelZoom={false}>
        <TileLayer
          attribution='&copy; <a href="https://www.openstreetmap.org/copyright">OpenStreetMap</a> contributors'
          url="https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png"
        />
        <Marker position={[59.436, 24.744]}>
          <Popup>
            Vanalinna kontor. <br /> Avatud 10-20.
          </Popup>
        </Marker>
        <Marker position={[59.422, 24.793]}>
          <Popup>
            Ülemiste kontor. <br /> Avatud 8-22.
          </Popup>
        </Marker>
      </MapContainer>
    </div>
  )
}

export default Shops