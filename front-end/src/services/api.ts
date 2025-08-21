import type { DashboardData, Cliente, Modulo } from "../types";

const BASE_URL = import.meta.env.VITE_API_BASE_URL;

export async function getDashboardData(ano: number, mes: number): Promise<DashboardData> {
  const response = await fetch(`${BASE_URL}/dashboard?ano=${ano}&mes=${mes}`);
  if (!response.ok) 
    throw new Error("Falha ao buscar dados do dashboard");
  return response.json();
}

export async function getClientes(): Promise<Cliente[]>{
  const response = await fetch(`${BASE_URL}/clientes`);
  if(!response.ok)
    throw new Error('Falha ao buscar clientes');
  return response.json();
}

export async function getModulos(): Promise<Modulo[]>{
  const response = await fetch(`${BASE_URL}/modulos`);
  if(!response.ok)
    throw new Error('Falha ao buscar módulos');
  return response.json();
}