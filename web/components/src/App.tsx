import { useState } from "react";
import "./index.css";

function App() {
  const [count, setCount] = useState(0);

  return (
    <div>
      <p className="bg-cyan-500">
        Click on the Vite and React logos to learn more
      </p>
    </div>
  );
}

export default App;
