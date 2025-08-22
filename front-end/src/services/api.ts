import type { DashboardData, Cliente, Modulo } from "../types";

const BASE_URL = import.meta.env.VITE_API_BASE_URL;

export async function getDashboardDataByMonthAndYear(ano: number, mes: number, page: number = 0, size: number = 10): Promise<DashboardData> {
  const response = await fetch(`${BASE_URL}/dashboard/by-month-and-year?ano=${ano}&mes=${mes}&page=${page}&size=${size}&sort=id,desc`);
  if (!response.ok) 
    throw new Error("Falha ao buscar dados do dashboard por ano e mês");
  return response.json();
}

export async function getDashboardDataByMonth(mes: number, page: number = 0, size: number = 10): Promise<DashboardData> {
  const response = await fetch(`${BASE_URL}/dashboard/by-month?mes=${mes}&page=${page}&size=${size}&sort=id,desc`);
  if (!response.ok) {
    throw new Error('Falha ao buscar dados do dashboard por mês');
  }
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