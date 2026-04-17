import { useEffect, useState } from "react";

function App() {
  const [experiments, setExperiments] = useState([]);
  const [name, setName] = useState("");

  const loadExperiments = () => {
    fetch("http://localhost:8080/experiments")
      .then(res => res.json())
      .then(data => setExperiments(data));
  };

  useEffect(() => {
    loadExperiments();
    const interval = setInterval(loadExperiments, 2000);
    return () => clearInterval(interval);
  }, []);

  const createExperiment = () => {
    fetch("http://localhost:8080/experiments", {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify({
        name: name,
        parameters: "{}",
      }),
    }).then(() => {
      setName("");
      loadExperiments();
    });
  };

  const startExperiment = (id) => {
    fetch(`http://localhost:8080/experiments/${id}/start`, {
      method: "POST",
    }).then(loadExperiments);
  };

  return (
    <div>
      <h1>Experiments</h1>

      <input
        value={name}
        onChange={e => setName(e.target.value)}
        placeholder="Experiment name"
      />
      <button onClick={createExperiment}>Create</button>

      <ul>
        {experiments.map(exp => (
        <li key={exp.id}>
          {exp.name} - {exp.status}
          <button onClick={() => startExperiment(exp.id)}>Start</button>
        </li>
        ))}
      </ul>
    </div>
  );
}

export default App;