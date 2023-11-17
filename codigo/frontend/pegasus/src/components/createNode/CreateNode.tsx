import * as Dialog from "@radix-ui/react-dialog";
import * as Toolbar from "@radix-ui/react-toolbar";
import { useState } from "react";
import SelectDemo from "../select/Select";

interface CreateNodeProps {
  onNodeDataChange: (data: NodeData) => void;
  nodeName: string;
}

interface NodeData {
  label: string;
  type: string;
}

interface NodeStyles {
  [key: string]: string;
}

export default function CreateNode({
  onNodeDataChange,
  nodeName,
}: CreateNodeProps) {
  const [label, setLabel] = useState<string>("");
  const [type, setType] = useState<string>(nodeName);

  const handleLabelChange = (newLabel: string) => {
    setLabel(newLabel);
  };

  const handleTypeChange = (newType: string) => {
    setType(newType);
  };

  const clearNode = () => {
    const data: NodeData = {
      label: label,
      type: type,
    };
    onNodeDataChange(data);
    setLabel("");
    setType(nodeName);
  };

  const nodeStyles: NodeStyles = {
    Worth: "bg-blue-500",
    GreenBeer: "bg-green-500",
    Filtration: "bg-amber-950",
    Mixproof: "bg-orange-500",
    Solenoide: "bg-pink-500",
    Tank: "bg-yellow-500",
  };

  const nodeStyleClass = nodeStyles[nodeName];

  return (
    <Dialog.Root>
      <Dialog.Trigger asChild>
        <Toolbar.Button className={`w-24 h-12 mt-4 ${nodeStyleClass} rounded`}>
          <p className="text-white text-center">{nodeName}</p>
        </Toolbar.Button>
      </Dialog.Trigger>
      <Dialog.Portal>
        <Dialog.Overlay className="bg-blackA9 data-[state=open]:animate-overlayShow fixed inset-0" />
        <Dialog.Content className="data-[state=open]:animate-contentShow fixed top-[50%] left-[50%] max-h-[85vh] w-[90vw] max-w-[450px] translate-x-[-50%] translate-y-[-50%] rounded-[6px] bg-white p-[25px] shadow-[hsl(206_22%_7%_/_35%)_0px_10px_38px_-10px,_hsl(206_22%_7%_/_20%)_0px_10px_20px_-15px] focus:outline-none">
          <Dialog.Title className="text-mauve12 m-0 text-[17px] font-medium">
            Add {nodeName} Node
          </Dialog.Title>
          <Dialog.Description className="text-mauve11 mt-[10px] mb-5 text-[15px] leading-normal"></Dialog.Description>
          <div className="flex flex-col gap-4">
            <p className="text-mauve12 text-[15px] leading-[19px] font-medium mb-2.5">
              Properties of Node
            </p>
            <fieldset className="flex gap-5 items-center">
              <label
                className="text-[13px] text-violet11 w-[75px]"
                htmlFor="width"
              >
                Name
              </label>
              <input
                className="w-full inline-flex items-center justify-center flex-1 rounded px-2.5 text-[13px] leading-none text-violet11 shadow-[0_0_0_1px] shadow-violet7 h-[25px] focus:shadow-[0_0_0_2px] focus:shadow-violet8 outline-none"
                id="width"
                value={label}
                onChange={(e) => handleLabelChange(e.target.value)}
              />
            </fieldset>
            <fieldset className="flex gap-5 items-center">
              <label
                className="text-[13px] text-violet11 w-[75px] mr-5"
                htmlFor="maxWidth"
              >
                Type
              </label>
              <SelectDemo type={type} onValueChange={handleTypeChange} />
            </fieldset>
          </div>
          <div className="mt-[25px] flex justify-end">
            <Dialog.Close asChild>
              <button
                className="bg-green4 text-green11 hover:bg-green5 focus:shadow-green7 inline-flex h-[35px] items-center justify-center rounded-[4px] px-[15px] font-medium leading-none focus:shadow-[0_0_0_2px] focus:outline-none"
                onClick={clearNode}
              >
                Add
              </button>
            </Dialog.Close>
          </div>
          <Dialog.Close asChild>
            <button
              className="text-violet11 hover:bg-violet4 focus:shadow-violet7 absolute top-[10px] right-[10px] inline-flex h-[25px] w-[25px] appearance-none items-center justify-center rounded-full focus:shadow-[0_0_0_2px] focus:outline-none"
              aria-label="Close"
            ></button>
          </Dialog.Close>
        </Dialog.Content>
      </Dialog.Portal>
    </Dialog.Root>
  );
}
