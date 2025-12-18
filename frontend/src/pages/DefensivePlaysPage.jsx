import { useEffect, useState } from 'react'
import { fetchPlayer, fetchDefensivePlays } from '../api/SavantAPI'
import '../DefensivePlaysPage.css'

function DefensivePlaysPage() {
  const [plays, setPlays] = useState([])
  const [loading, setLoading] = useState(null)
  const [error, setError] = useState(null)
  const [season, setSeason] = useState("2025")
  const [playerName, setPlayerName] = useState("Fernando Tatis Jr")
  const [sortDirection, setSortDirection] = useState("asc")
  const [sortKey, setSortKey] = useState(null)

  const toggleSortDirection = () => {
    setSortDirection(prev => (prev === "asc" ? "desc" : "asc"));
  }

  const handleSort = (key) => {
    const nextDirection =
    sortKey === key && sortDirection === "asc" ? "desc" : "asc";

    setSortKey(key);
    setSortDirection(nextDirection);

    const sorted = [...plays].sort((a, b) => {
      let valA = a[key];
      let valB = b[key];
  
      // If strings, compare case-insensitively
      if (typeof valA === "string") {
        valA = valA.toLowerCase();
        valB = valB.toLowerCase();
      }
  
      if (valA < valB) return nextDirection === "asc" ? -1 : 1;
      if (valA > valB) return nextDirection === "asc" ? 1 : -1;
      return 0;
    });
  
    setPlays(sorted);
  }

  const fetchPlays = async () => {
    try {
      setLoading(true);
      const player = await fetchPlayer(playerName);
      const data = await fetchDefensivePlays(player.id, season);
      setPlays(data);
    } catch (err) {
      setError(err.message);
    } finally {
      setLoading(false);
    }
  }

/*   useEffect(() => {
    const fetchPlays = async () => {
      try {
        const data = await fetchDefensivePlays(665487,2025)
        setPlays(data);
      } catch (err) {
        setError(err.message);
      } finally {
        setLoading(false);
      }
    }
    fetchPlays();
  }, []) */

  if (loading) return (
    <>
      <h1>catching this...</h1>
      <p>loading plays...</p>
    </>
  )
  if (error) return (
    <>
      <h1>dropped it!</h1>
      <p>error: {error}</p>
    </>
  )

  return (
    <>
      <div className="page">
        <header className='header'>
          <h1>catch this!</h1>
        </header>

        <main className='content'>
          <div className='controls'>
            <label>
              player name:
              <input
                type="text"
                value={playerName}
                onChange={(e) => setPlayerName(e.target.value)}
                style={{ marginLeft: "0.5rem", marginRight: "1rem" }}
              />
            </label>

            <label>
              season:
              <input
                type="number"
                value={season}
                onChange={(e) => setSeason(e.target.value)}
                min="1900"
                max={new Date().getFullYear()}
                style={{ marginLeft: "0.5rem", marginRight: "1rem" }}
              />
            </label>
          </div>
        </main>

        <button onClick={fetchPlays}>load plays</button>
      </div>

      <table className='plays-table'>
        <thead>
          <tr>
          <th onClick={() => handleSort("gamePk")}>
            Game <span className="sort-indicator">{sortKey === "gamePk" ? (sortDirection === "asc" ? "▲" : "▼") : ""}</span>
          </th>
          <th onClick={() => handleSort("playId")}>
            Play ID <span className="sort-indicator">{sortKey === "playId" ? (sortDirection === "asc" ? "▲" : "▼") : ""}</span>
          </th>
          <th onClick={() => handleSort("playerName")}>
            Player <span className="sort-indicator">{sortKey === "playerName" ? (sortDirection === "asc" ? "▲" : "▼") : ""}</span>
          </th>
          <th onClick={() => handleSort("catchRate")}>
            Catch Rate <span className="sort-indicator">{sortKey === "catchRate" ? (sortDirection === "asc" ? "▲" : "▼") : ""}</span>
          </th>
          <th onClick={() => handleSort("distance")}>
            Distance <span className="sort-indicator">{sortKey === "distance" ? (sortDirection === "asc" ? "▲" : "▼") : ""}</span>
          </th>
          <th onClick={() => handleSort("hangTime")}>
            Hang Time <span className="sort-indicator">{sortKey === "hangTime" ? (sortDirection === "asc" ? "▲" : "▼") : ""}</span>
          </th>
          </tr>
        </thead>
        <tbody>
          {plays.map(play => (
            <tr key={play.playId}>
              <td>{play.gamePk}</td>
              <td>{play.playId}</td>
              <td>{play.playerName}</td>
              <td>{play.catchRate}</td>
              <td>{play.distance}</td>
              <td>{play.hangTime}</td>
            </tr>
          ))}
        </tbody>
      </table>
{/*       <ul>
        {plays.map(play => (
          <li key={play.playId}>
            <strong>{play.playerName}</strong> — Catch Rate: {play.catchRate}
          </li>
        ))}
      </ul> */}
    </>
  )
}

export default DefensivePlaysPage
