export interface Ticket{
  id: number;
  titulo: string;
  cliente: string;
  modulo: string;
  dataAbertura: string;
  dataEncerramento: string | null;
}

export interface DashboardData {
  listaTickets: Ticket[];
  agrupadoPorCliente: Record<string, number>;
  agrupadoPorModulo: Record<string, number>;
}

export interface Cliente{
  id: number;
  nome: string;
}

export interface Modulo{
  id: number;
  nome: string;
}