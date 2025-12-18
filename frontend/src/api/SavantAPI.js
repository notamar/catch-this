export async function fetchDefensivePlays(playerId, season) {
  const response = await fetch(
    `/api/v1/catch-probability?playerId=${playerId}&season=${season}`
  )

  if (!response.ok) {
    throw new Error('failed to fetch defensive plays')
  }

  return response.json();
}

export async function fetchPlayer(playerName) {
  const response = await fetch(
    `/api/v1/people?playerName=${playerName}`
  )

  if (!response.ok) {
    throw new Error('failed to fetch player id. check name spelling perhaps')
  }

  return response.json();
}