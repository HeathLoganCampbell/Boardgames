const canvas = document.getElementById('gameCanvas');
const ctx = canvas.getContext('2d');
const gridSize = 10;
const cellSize = canvas.width / gridSize;

let ships = []; // Array to store ship positions
let highlightedCell = null; // Store the current highlighted cell

// Draw the grid
function drawGrid() {
    ctx.clearRect(0, 0, canvas.width, canvas.height);

    for (let x = 0; x <= canvas.width; x += cellSize) {
        ctx.moveTo(x, 0);
        ctx.lineTo(x, canvas.height);
    }

    for (let y = 0; y <= canvas.height; y += cellSize) {
        ctx.moveTo(0, y);
        ctx.lineTo(canvas.width, y);
    }

    ctx.strokeStyle = "#000";
    ctx.stroke();
}

// Draw ships
function drawShips() {
    ctx.fillStyle = "navy";
    ships.forEach(ship => {
        ctx.fillRect(ship.x * cellSize, ship.y * cellSize, cellSize, cellSize);
    });
}

// Highlight a cell
function highlightCell(x, y) {
    if (highlightedCell) {
        ctx.clearRect(highlightedCell.x * cellSize, highlightedCell.y * cellSize, cellSize, cellSize);
        drawGrid(); // Redraw grid to remove previous highlight
        drawShips(); // Redraw ships to ensure they are visible
    }

    highlightedCell = { x: x, y: y };
    ctx.fillStyle = "rgba(255, 255, 0, 0.5)"; // Semi-transparent yellow
    ctx.fillRect(x * cellSize, y * cellSize, cellSize, cellSize);
}

// Add a ship at a specific grid location
function addShip(x, y) {
    ships.push({ x: x, y: y });
    drawShips();
}

// Handle canvas mousemove
canvas.addEventListener('mousemove', function(event) {
    const rect = canvas.getBoundingClientRect();
    const x = event.clientX - rect.left;
    const y = event.clientY - rect.top;
    const gridX = Math.floor(x / cellSize);
    const gridY = Math.floor(y / cellSize);

    highlightCell(gridX, gridY);
});

// Handle canvas click
canvas.addEventListener('click', function(event) {
    if (highlightedCell) {
        addShip(highlightedCell.x, highlightedCell.y);
        // Add logic to send ship position to the server
    }
});

drawGrid();
drawShips();


// Create a new WebSocket connection
var ws = new WebSocket("ws://localhost:8080/websocket");

// Connection opened
ws.onopen = function(event) {
    console.log("Connected to the WebSocket server");

    // Send a message to the server
    var joinMatchPacket = {
        type: 'joinMatch',
        data: {
            gameId: "12345",
            username: "player1"
        }
    };
    ws.send(JSON.stringify(joinMatchPacket));
};

// Listen for messages
ws.onmessage = function(event) {
    console.log("Message from server ", event.data);
};

// Listen for errors
ws.onerror = function(error) {
    console.error("WebSocket error observed:", error);
};

// Connection closed
ws.onclose = function(event) {
    console.log("WebSocket connection closed");
};


setInterval(() => {
    ws.send(JSON.stringify({ type: "keepAlive", data: {} }));
}, 10_000);

function launchMissile(x, y) {
    ws.send(JSON.stringify({ type: "LaunchMissilePacket", x: x, y: y }));
}

function createMatch() {
    ws.send(JSON.stringify({ type: "CreateMatchPacket" }));
}

function joinMatch(matchId) {
    ws.send(JSON.stringify({ type: "JoinMatchPacket", matchId: matchId }));
}
