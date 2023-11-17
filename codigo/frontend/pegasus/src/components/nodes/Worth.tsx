import { Handle, Position, NodeProps } from "reactflow";
import * as Popover from "@radix-ui/react-popover";

import worth from "../../assets/img/worth.svg";

// função que renderiza o nó de Worth
export default function Worth(props: NodeProps) {
  return (
    <Popover.Root>
      <Popover.Trigger asChild>
        <div className="rounded w-[180px] h-[100px] flex items-center justify-center text-white text-center">
          <img src={worth} alt="Worth node" width={180}/>
          <Handle
            id="right"
            type="source"
            position={Position.Right}
            className="-right-4 w-3 h-3 border-2 bg-blue-400/80"
          />
        </div>
      </Popover.Trigger>
      <Popover.Portal>
        <Popover.Content
          className="rounded p-5 min-w-[260px] max-w-xs bg-white shadow-[0_10px_38px_-10px_hsla(206,22%,7%,.35),0_10px_20px_-15px_hsla(206,22%,7%,.2)] focus:shadow-[0_10px_38px_-10px_hsla(206,22%,7%,.35),0_10px_20px_-15px_hsla(206,22%,7%,.2),0_0_0_2px_theme(colors.violet7)] will-change-[transform,opacity] data-[state=open]:data-[side=top]:animate-slideDownAndFade data-[state=open]:data-[side=right]:animate-slideLeftAndFade data-[state=open]:data-[side=bottom]:animate-slideUpAndFade data-[state=open]:data-[side=left]:animate-slideRightAndFade"
          sideOffset={5}
        >
          <div className="flex flex-col gap-4">
            <p className="text-mauve12 text-[15px] leading-[19px] font-medium mb-2.5">
              Propriedades
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
                defaultValue={props.data.label}
                disabled={true}
              />
            </fieldset>
            <fieldset className="flex gap-5 items-center">
              <label
                className="text-[13px] text-violet11 w-[75px]"
                htmlFor="maxWidth"
              >
                Type
              </label>
              <input
                className="w-full inline-flex items-center justify-center flex-1 rounded px-2.5 text-[13px] leading-none text-violet11 shadow-[0_0_0_1px] shadow-violet7 h-[25px] focus:shadow-[0_0_0_2px] focus:shadow-violet8 outline-none"
                id="maxWidth"
                defaultValue={props.type}
                disabled={true}
              />
            </fieldset>
          </div>
          <Popover.Close
            className="rounded-full h-[25px] w-[25px] inline-flex items-center justify-center text-violet11 absolute top-[5px] right-[5px] hover:bg-violet4 focus:shadow-[0_0_0_2px] focus:shadow-violet7 outline-none cursor-default"
            aria-label="Close"
          ></Popover.Close>
          <Popover.Arrow className="fill-white" />
        </Popover.Content>
      </Popover.Portal>
    </Popover.Root>
  );
}
