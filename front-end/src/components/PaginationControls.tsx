import {
  Pagination,
  PaginationContent,
  PaginationEllipsis,
  PaginationItem,
  PaginationLink,
  PaginationNext,
  PaginationPrevious,
} from "@/components/ui/pagination";
import { DOTS, usePagination } from "@/hooks/usePagination";

interface PaginationControlsProps {
  totalPages: number;
  currentPage: number; 
  onPageChange: (page: number) => void;
}

export function PaginationControls({ totalPages, currentPage, onPageChange }: PaginationControlsProps) {
  const paginationRange = usePagination({ currentPage: currentPage + 1, totalPages });

  const onNext = () => {
    if (currentPage + 1 < totalPages) {
      onPageChange(currentPage + 1);
    }
  };

  const onPrevious = () => {
    if (currentPage > 0) {
      onPageChange(currentPage - 1);
    }
  };

  return (
    <Pagination>
      <PaginationContent>
        <PaginationItem>
          <PaginationPrevious
            href="#"
            onClick={(e) => { e.preventDefault(); onPrevious(); }}
            className={currentPage === 0 ? 'pointer-events-none opacity-50' : ''}
          />
        </PaginationItem>

        {paginationRange.map((pageNumber, index) => {
          if (pageNumber === DOTS) {
            return <PaginationItem key={`dots-${index}`}><PaginationEllipsis /></PaginationItem>;
          }

          const key = typeof pageNumber === 'number' ? pageNumber : `extra-${index}`;

          return (
            <PaginationItem key={key}>
              <PaginationLink
                href="#"
                onClick={(e) => { 
                  e.preventDefault(); 
                  if (typeof pageNumber === 'number') {
                    onPageChange(pageNumber - 1);
                  }
                }}
                isActive={typeof pageNumber === 'number' && (pageNumber - 1 === currentPage)}
              >
                {pageNumber}
              </PaginationLink>
            </PaginationItem>
          );
        })}

        <PaginationItem>
          <PaginationNext
            href="#"
            onClick={(e) => { e.preventDefault(); onNext(); }}
            className={currentPage + 1 >= totalPages ? 'pointer-events-none opacity-50' : ''}
          />
        </PaginationItem>
      </PaginationContent>
    </Pagination>
  );
}