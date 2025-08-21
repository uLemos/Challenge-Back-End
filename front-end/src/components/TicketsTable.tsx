import type { Ticket } from "../types";
import { Card, CardContent, CardHeader, CardTitle } from "./ui/card";
import { Table, TableBody, TableCell, TableHead, TableHeader, TableRow } from "./ui/table";

interface TicketsTableProps {
  tickets: Ticket[];
}

export default function TicketsTable({ tickets }: TicketsTableProps) {
  return (
    <Card>
      <CardHeader>
        <CardTitle>Detalhes dos Chamados</CardTitle>
      </CardHeader>
      <CardContent>
        <Table>
          <TableHeader>
            <TableRow>
              <TableHead>Código</TableHead>
              <TableHead>Título</TableHead>
              <TableHead>Cliente</TableHead>
              <TableHead>Data Abertura</TableHead>
              <TableHead>Data Encerramento</TableHead>
              <TableHead>Módulo</TableHead>
            </TableRow>
          </TableHeader>
          <TableBody>
            {tickets.map((ticket) => (
              <TableRow key={ticket.id}>
                <TableCell className="font-medium">{ticket.id}</TableCell>
                <TableCell>{ticket.titulo}</TableCell>
                <TableCell>{ticket.cliente}</TableCell>
                <TableCell>
                  {new Date(ticket.dataAbertura).toLocaleDateString('pt-BR')}
                </TableCell>
                <TableCell>
                  {ticket.dataEncerramento
                    ? new Date(ticket.dataEncerramento).toLocaleDateString('pt-BR')
                    : '—'}
                </TableCell>
                <TableCell>{ticket.modulo}</TableCell>
              </TableRow>
            ))}
          </TableBody>
        </Table>
      </CardContent>
    </Card>
  );
}