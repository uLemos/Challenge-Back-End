import { useEffect, useState } from 'react'
import type { DashboardData } from './types'
import { getDashboardDataByMonth } from './services/api';
import './index.css'
import DashboardCharts from './components/DashboardCharts';
import { Select, SelectContent, SelectItem, SelectTrigger, SelectValue } from './components/ui/select';
import TicketsTable from './components/TicketsTable';
import { PaginationControls } from './components/PaginationControls';

const MESES = [
  { value: 1, label: 'Janeiro' }, { value: 2, label: 'Fevereiro' }, { value: 3, label: 'Março' },
  { value: 4, label: 'Abril' }, { value: 5, label: 'Maio' }, { value: 6, label: 'Junho' },
  { value: 7, label: 'Julho' }, { value: 8, label: 'Agosto' }, { value: 9, label: 'Setembro' },
  { value: 10, label: 'Outubro' }, { value: 11, label: 'Novembro' }, { value: 12, label: 'Dezembro' }
];

export default function App() {

  const [paginaAtual, setPaginaAtual] = useState(0);
  const [data, setData] = useState<DashboardData | null>(null);
  const [loading, setLoading] = useState<boolean>(true);
  const [error, setError] = useState<string | null>(null);

  const [mesSelecionado, setMesSelecionado] = useState<number>(3);

  useEffect(() => {
    const fetchDashboardData = async () => {
      setLoading(true); 
      setError(null);
      try {
        const dashboardData = await getDashboardDataByMonth(mesSelecionado, paginaAtual);
        setData(dashboardData);
      } catch (err) {
        setError('Não foi possível carregar os dados da API. Verifique os filtros ou se o back-end está rodando.');
        console.error(err);
      } finally {
        setLoading(false);
      }
    };
    fetchDashboardData();
  }, [mesSelecionado, paginaAtual]);

  if (loading)
    return <div className="p-4">Carregando dados do dashboard...</div>;

  if (error)
    return <div className='text-red-500'>{error}</div>;

  return (
    <main className="min-h-screen p-4 md:p-8 font-sans">
      <div className="max-w-7xl mx-auto">
        
        {data && !loading && (
          <DashboardCharts 
            dataCliente={data.agrupadoPorCliente} 
            dataModulo={data.agrupadoPorModulo} 
          />
        )}
        
        <div className="mt-6">
          <div className="flex items-center gap-4 mb-4 p-4 rounded-lg">
            <h2 className="text-lg font-semibold whitespace-nowrap">Chamados do Mês de:</h2>
            <Select value={String(mesSelecionado)} onValueChange={(value) => setMesSelecionado(Number(value))}>
              <SelectTrigger className="w-[180px]">
                <SelectValue placeholder="Selecione o Mês" />
              </SelectTrigger>
              <SelectContent >
                {MESES.map(mes => <SelectItem key={mes.value} value={String(mes.value)}>{mes.label}</SelectItem>)}
              </SelectContent>
            </Select>
          </div>

          {loading && <div className="p-4 text-center">Carregando dados...</div>}
          {error && <div className="p-4 text-red-500 text-center">{error}</div>}
          {data && !loading && (
            <>
              <TicketsTable tickets={data.listaTickets.content} />
              <div className="flex items-center justify-center space-x-2 py-4">
                {data.listaTickets.totalPages > 1 && (
                  <PaginationControls
                    currentPage={paginaAtual}
                    totalPages={data.listaTickets.totalPages}
                    onPageChange={setPaginaAtual}
                  />
                )}
              </div>
            </>
          )}
        </div>

      </div>
    </main>
  );
}

