import express from "express";
import path from 'path';
import { fileURLToPath } from 'url';

const __filename = fileURLToPath(import.meta.url);
const __dirname = path.dirname(__filename);


const PORT = process.env.CLIENT_PORT || 8080
export { PORT as PORT };

const app = express()

app.use(express.static(__dirname + "/dist"));

app.get('/*', (req, res) => {
    res.sendFile(__dirname + '/index.html');
})

app.listen(PORT, () => {
    console.log(`Server listening on http://localhost:${PORT}`)
});