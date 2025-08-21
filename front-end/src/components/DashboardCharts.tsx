import { PieChart, Pie, Cell, ResponsiveContainer, Tooltip, Legend } from 'recharts';
import { Card, CardContent, CardHeader, CardTitle } from './ui/card';

interface DashboardChartsProps {
  dataCliente: Record<string, number>;
  dataModulo: Record<string, number>;
}

const COLORS = ['#0088FE', '#00C49F', '#FFBB28', '#FF8042', '#AF19FF'];

export default function DashboardCharts({ dataCliente, dataModulo }: DashboardChartsProps) {

  const chartDataCliente = Object.entries(dataCliente).map(([name, value]) => ({ name, value }));
  const chartDataModulo = Object.entries(dataModulo).map(([name, value]) => ({ name, value }));

  return (
    <div className="grid gap-6 md:grid-cols-2 lg:grid-cols-2 mb-6">
      <Card>
        <CardHeader>
          <CardTitle>Chamados por Cliente</CardTitle>
        </CardHeader>
        <CardContent>
          <ResponsiveContainer width="100%" height={300}>
            <PieChart>
              <Pie
                data={chartDataCliente}
                dataKey="value"
                nameKey="name"
                cx="50%"
                cy="50%"
                outerRadius={110}
                fill="#8884d8"
                label
              >
                {chartDataCliente.map((_entry, index) => (
                  <Cell key={`cell-${index}`} fill={COLORS[index % COLORS.length]} />
                ))}
              </Pie>
              <Tooltip />
              <Legend />
            </PieChart>
          </ResponsiveContainer>
        </CardContent>
      </Card>

      <Card>
        <CardHeader>
          <CardTitle>Chamados por Módulo</CardTitle>
        </CardHeader>
        <CardContent>
          <ResponsiveContainer width="100%" height={300}>
            <PieChart>
              <Pie
                data={chartDataModulo}
                dataKey="value"
                nameKey="name"
                cx="50%"
                cy="50%"
                outerRadius={110}
                fill="#82ca9d"
                label
              >
                {chartDataModulo.map((_entry, index) => (
                  <Cell key={`cell-${index}`} fill={COLORS[index % COLORS.length]} />
                ))}
              </Pie>
              <Tooltip />
              <Legend />
            </PieChart>
          </ResponsiveContainer>
        </CardContent>
      </Card>
    </div>
  );
}