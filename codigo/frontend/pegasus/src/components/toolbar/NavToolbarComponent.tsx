import * as Toolbar from "@radix-ui/react-toolbar";

// Componente que renderiza a barra de navegação
interface NavToolbarComponentProps {
  name: string;
  onNameChange: (newName: string) => void;
}

// função que renderiza a barra de navegação
export default function NavToolbarComponent({
  name,
  onNameChange
}: NavToolbarComponentProps) {

  // função que atualiza o nome do projeto
  function handleNameChange(event: React.ChangeEvent<HTMLInputElement>) {
    onNameChange(event.target.value);
  }
  return (
    <Toolbar.Root className="fixed top-28 left-44 -translate-x-1/2 flex justify-center items-center bg-white rounded-xl shadow-lg border border-zinc-300 h-16 max-w-[230px] overflow-hidden">
    <div className="flex justify-center items-center">
      <input
        className="font-montserrat text-center font-bold w-5/6 text-lg focus:outline-none"
        type="text"
        value={name}
        onChange={handleNameChange}
        required
      />
      </div>
    </Toolbar.Root>
  );
}